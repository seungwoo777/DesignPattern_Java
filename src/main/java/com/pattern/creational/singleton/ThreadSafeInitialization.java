package com.pattern.creational.singleton;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 동기화를 통해 메소드에 Thread들을 하나하나씩 접근하도록 설정한다.
 * 하지만 여러개의 모듈들이 매번 객체를 가져올 때 synchronized 메소드를 매번 호출하여 동기화하여 처리 작업에 오버헤드 발생 할 수 있어 성능이 하락된다.
 * synchronized 키워드는 멀티 쓰레드 환경에서 두개 이상의 쓰레드가 하나의 변수에 동시에 접근을 할 때 Race condition(경쟁 상태)이 발생하지 않도록 한다.
 * Thread가 해당 메소드를 실행하는 동안 다른 Thread가 접근하지 못하도록 lock을 거는 것이다.
 * 만약 Thread가 4개있다 가정하면 Thread-1이 메소드에 진입하는 순간 나머지(Thread-2~4)의 접근을 제한한다.
 * Thread-1이 완료되면 다음 Thread가 접근한다.
 */
public class ThreadSafeInitialization {

    private static ThreadSafeInitialization instance;

    public static synchronized ThreadSafeInitialization getInstance() {
        if(instance == null) instance = new ThreadSafeInitialization();
        return instance;
    }

    public static void main(String[] args) {
        ThreadSafeInitialization[] threadSafeInitializations = new ThreadSafeInitialization[10];

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 10개 Thread 동시 인스턴스 생성
        for(int i = 0; i < 10; i++) {
            final int num = i;

            executorService.submit(() -> {
               threadSafeInitializations[num] = ThreadSafeInitialization.getInstance();
            });
        }

        executorService.shutdown();

        /*
           synchronized 키워드를 통해 하나 하나 씩 접근 주소가 모두 같다
           하지만 위 설명 처럼 여러개의 모듈들이 매번 객체를 가져올 때 synchronized 메소드를 매번 호출하여 동기화 처리를 할 시
           작업에 오버헤드가 발생할 수 있다.(성능 하락 발생)
         */
        Arrays.stream(threadSafeInitializations).forEach(System.out::println);
    }
}
