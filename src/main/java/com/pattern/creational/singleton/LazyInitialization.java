package com.pattern.creational.singleton;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 객체 생성에 대한 관리를 내부적으로 처리한다.
 * 메소드를 호출했을 때 인스턴스 변수의 null 유무에 따라 초기화 하거나 반환하는 기법이다.
 * 미사용 고정 메모리 차지의 한계점을 극복한 방법이다.
 * 그러나 Thread Safe 하지 않는 치명적인 단점을 가지고 있다.
 */
public class LazyInitialization {

    private static LazyInitialization instance;

    // 생성자를 private으로 선언 하여 외부에서 new 사용 X
    private LazyInitialization() {};

    // 외부에서 정적 메소드를 호출하면 그제서야 초기화 진행(Lazy) 한다.
    public static LazyInitialization getInstance() {
        if(instance == null) instance = new LazyInitialization();
        return instance;
    }

    public static void main(String[] args) {
        LazyInitialization[] lazyInitializations = new LazyInitialization[10];

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 10개 Thread를 동시에 인스턴스 생성
        for(int i = 0; i < 10; i++) {
            final int num = i;

            executorService.submit(() -> {
               lazyInitializations[num] = LazyInitialization.getInstance();
            });
        }

        executorService.shutdown();

        // Singleton 객체 주소 출력
        Arrays.stream(lazyInitializations).forEach(System.out::println);

        /*
         !! Singleton인데 객체가 두개 생성되었다.
         com.pattern.creational.singleton.LazyInitialization@1b6d3586 X 9
         com.pattern.creational.singleton.LazyInitialization@4554617c -- 요기
         */
    }
}
