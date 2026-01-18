package com.pattern.creational.singleton;

import lombok.Getter;

/**
 * static block을 이용해 예외을 잡을 수 있다.
 * 그러나 여전히 static의 특성으로 사용하지 않는데도 메모리 공간을 차지한다.
 * static block -> 클래스가 로딩되고 클래스 변수가 준비된 후 자동으로 실행되는 블록이다.
 */
public class StaticBlockInitialization {

    // Singleton Class 객체를 담을 인스턴스 변수
    @Getter
    private static StaticBlockInitialization instance;

    // static 블록을 이용해 예외 처리
    static {
        try {
            instance = new StaticBlockInitialization();
        }
        catch (Exception e) {
            throw new RuntimeException("Singleton 객체 생성 오류", e);
        }
    }

    public static void main(String[] args) {

        StaticBlockInitialization singleton1 = StaticBlockInitialization.getInstance();
        StaticBlockInitialization singleton2 = StaticBlockInitialization.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);

        System.out.println(singleton1 == singleton2);
    }
}
