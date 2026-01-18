package com.pattern.creational.singleton;

/**
 * 한번만 미리 만들어 두는 가장 직관적이면서 심플한 기법이다.
 * static final이라 멀티 쓰레드 환경에서도 안전하다.
 * 그러나 static 멤버는 당장 객체를 사용하지 않더라도 메모리에 적재하기 때문에 만일 리소스가 큰 객체일 경우 공간 자원낭비가 발생한다.
 * 예외 처리를 할수 없다.
 * 싱글톤을 적용한 객체가 그리 크지 않은 객체라면 이 기법으로 적용해도 무리는 없다.
 */
public class EagerInitialization {

    // Singleton Class 객체를 담을 인스턴스 변수
    private static final EagerInitialization INSTANCE = new EagerInitialization();

    // 생성자를 private으로 선언 하여 외부에서 new 사용 X
    private EagerInitialization() {}

    public static EagerInitialization getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) {

        EagerInitialization singleton1 = EagerInitialization.getInstance();
        EagerInitialization singleton2 = EagerInitialization.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton1 == singleton2);
    }

}
