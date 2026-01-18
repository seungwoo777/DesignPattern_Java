package com.pattern.structural.adaptor.object;

public class ObjectAdapter implements ObjectTarget {

    // composition으로 Service 객체를 클래스 필드로
    ObjectService adaptee;

    // Adapter가 인스턴스화 되면 호환시킬 기존 서비스를 설정한다.
    public ObjectAdapter(ObjectService adaptee) {
        this.adaptee = adaptee;
    }

    // Adapter의 메소드가 호출되면 Adaptee의 메소드를 호출하도록 위임한다.
    @Override
    public void method(int data) {
        adaptee.specificMethod(data);
    }

}
