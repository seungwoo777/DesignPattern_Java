package com.pattern.creational.singleton;

/**
 * LazyHolder 라고도 불림. 권장되는 싱글톤 기법 두개 중 하나이다.(1/2)
 * 멀티 쓰레드 환경에서 안전하고 Lazy Loading(나중에 객체 생성)도 가능한 완벽한 싱글톤 기법이다.
 * 클래스 안에 내부 클래스(holder)를 두어 JVM의 클래스 로더 매커니즘과 클래스가 로드되는 시점을 이용한 방법이다.(Thread-Safe)
 * static 메소드에서는 static 멤버만을 호출 할 수 있기 때문에 내부 클래스를 static으로 설정한다.
 * 이 밖에도 내부 클래스의 치명적인 문제점인 메모리 누수 문제를 해결하기 위하여 내부 클래스를 static으로 설정한다.
 * 다만 클라이언트가 임의로 Singleton을 파괴할 수 있다는 단점을 지닌다.(Reflection API, 직렬화/역직렬화를 통해)
 */
public class BillPughSolution {

    private BillPughSolution() {}

    // Inner Class를 이용한다.
    // Holder를 만들어 클래스가 메모리에 로드 되지 않고 getInstance 메소드가 호출되어야 로드 된다.
    private static class BillPughSolutionHolder {
        private static final BillPughSolution INSTANCE = new BillPughSolution();
    }

    public static BillPughSolution getInstance() {
        return BillPughSolutionHolder.INSTANCE;
    }

    /**
     * 1. 우선 Inner Class static으로 선언 하였기 때문에 Singleton Class가 초기화 되어도
     *    BillPughSolutionHolder 내부 클래스는 메모리에 로드되지 않는다.
     * 2. 어떠한 모듈에서 getInstance() 메소드를 호출 할 때 BillPughSolutionHolder Inner Class의
     *    static 멤버를 가져와 return하게 되는데 이 때 Inner Class가 한번만 초기화 되면서 Singleton 객체를 최초로 생성 및 리턴한다.
     * 3. 마지막으로 final로 선언하면서 다시 값이 할당 되지 않도록 방지한다.
     */
    public static void main(String[] args) {
        BillPughSolution singleton1 = BillPughSolution.getInstance();
        BillPughSolution singleton2 = BillPughSolution.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);

        System.out.println(singleton1 == singleton2);
    }
}
