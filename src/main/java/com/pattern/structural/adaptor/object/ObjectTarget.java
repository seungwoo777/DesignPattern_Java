package com.pattern.structural.adaptor.object;

/**
 * Client Interface: 클라이언트가 접근 해서 사용하게 할 수 있도록 호환 처리 해주는 Adaptor
 */
public interface ObjectTarget {
    void method(int data);
}
