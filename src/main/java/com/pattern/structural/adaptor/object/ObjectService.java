package com.pattern.structural.adaptor.object;

/**
 * Adaptee: 클라이언트에서 사용하고 싶은 기존의 서비스(하지만 호환이 안되서 바로 사용이 불가능하다)
 */
public class ObjectService {

    void specificMethod(int data) {
        System.out.println("기존 서비스 호출 -> " + data);
    }

}
