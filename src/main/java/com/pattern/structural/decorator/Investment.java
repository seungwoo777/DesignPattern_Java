package com.pattern.structural.decorator;

/*
 * 데코레이터 패턴 (동적 기능 추가)
 * 객체에 동적으로 새로운 기능을 추가할 수 있게 해주는 패턴
 * 상속 대신 조합(Composition)을 사용하여 런타임에 유연하게 기능을 확장
 */
public interface Investment {
    String getDescription();
    int getReturn();
}
