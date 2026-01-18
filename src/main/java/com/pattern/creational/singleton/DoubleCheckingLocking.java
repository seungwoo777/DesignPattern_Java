package com.pattern.creational.singleton;

/**
 * 매번 synchronized 동기화 실행하는 것이 문제라면 최초 초기화 할때만 적용하고 이미 만들어진 인스턴스를 반환할때는 사용하지 않도록 하는 기법이다.
 * 이때 인스턴스 필드에 volatile 키워드를 붙여주어야 I/O 불일치 문제를 해결 할 수 있다.
 * 그러나 volatile 키워드를 이용하기 위해선 JVM 1.5이상 이어야 한다.
 * JVM에 따라서 여전히 Thread-Safe 하지 않는 경우가 발생하여 사용하기를 지양한다.
 */
public class DoubleCheckingLocking {

    /**
     * Java에서는 Thread를 여러개 사용할 경우 성능을 위해서 각각의 Thread들은 변수를 메인 메모리부터 가져오는 것이 아닌
     * Cache Memory에서 가져오게 된다. 문제는 비동기로 변수값을 Cache로 저장하다가 각 Thread마다 할당되어 있는
     * Cache Memory의 변수 값이 불 일치 할 수 있다.
     * 그래서 volatile 키워드를 통해 이 변수는 Cache에서 읽지 말고 메인 메모리에서 읽어오도록 지정한다.
     */
    private static volatile DoubleCheckingLocking instance;

    private DoubleCheckingLocking() {}

    public static DoubleCheckingLocking getInstance() {
        if(instance == null) {
            synchronized (DoubleCheckingLocking.class) { // 메소드에 동기화 거는게 아닌 클래스 자체를 동기화한다.
                if(instance == null)
                    instance = new DoubleCheckingLocking(); // 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를 최소화한다.
            }
        }
        return instance; // 최초 초기화가 되면 앞으로 생성된 인스턴스만 반환한다.
    }

    public static void main(String[] args) {
        DoubleCheckingLocking singleton1 = DoubleCheckingLocking.getInstance();
        DoubleCheckingLocking singleton2 = DoubleCheckingLocking.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton1 == singleton2);
    }
}
