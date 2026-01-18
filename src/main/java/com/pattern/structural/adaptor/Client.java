package com.pattern.structural.adaptor;

import com.pattern.structural.adaptor.object.ObjectAdapter;
import com.pattern.structural.adaptor.object.ObjectService;
import com.pattern.structural.adaptor.object.ObjectTarget;

public class Client {
    public static void main(String[] args) {

        // 1. Adapter 생성
        ObjectTarget objectAdapter = new ObjectAdapter(new ObjectService());
        ClassTarget classAdapter = new ClassAdapter();

        // 2. 인터페이스의 스펙에 따라 메소드를 실행하면 기존 서비스의 메소드가 실행
        objectAdapter.method(111);
        classAdapter.method(222);
    }
}
