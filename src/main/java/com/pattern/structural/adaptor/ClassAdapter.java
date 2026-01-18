package com.pattern.structural.adaptor;

// Adapter: Adaptee 서비스를 클라이언트에서 사용하게 할 수 있또록 호환처리 해주는 Adapter
public class ClassAdapter extends ClassService implements ClassTarget {

    // Adapter의 메소드가 호출되면 부모 클래스 Adaptee의 메소드를 호출한다.
    public void method(int data) {
        specificMethod(data);
    }
}
