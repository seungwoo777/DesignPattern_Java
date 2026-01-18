package com.pattern.creational.singleton;

import lombok.Getter;

/**
 * 권장되는 싱글톤 기법 두개 중 하나이다.(2/2)
 * Enum은 애초에 멤버를 만들 때 private으로 만들고 한번만 초기화 하기 때문에 Thread-Safe 하다.
 * Enum 내에서 상수 뿐만 아니라 변수나 메소드를 선언해 사용이 가능하기 때문에 이를 이용해 싱글톤 클래스 처럼 응용이 가능하다.
 * BillPushSolution 기법과 달리 클라이언트에서 Reflection을 통한 공격에도 안전하다.
 * 하지만 만일 Singleton Class를 멀티톤(일반적인 클래스)으로 마이그레이션 해야 할 때 처음부터 코드를 다시 짜야되는 단점이 존재한다.
 * 클래스 상속이 필요할 때, Enum 외의 클래스 상속은 불가능하다.
 */
@Getter
public enum EnumSingleton {
    INSTANCE
    ;

    private final Thread thread;

    EnumSingleton() {
        thread = Thread.currentThread();
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton1 == singleton2);
    }
}
