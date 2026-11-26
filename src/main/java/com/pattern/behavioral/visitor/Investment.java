package com.pattern.behavioral.visitor;

/**
 * 투자 상품 인터페이스
 * 비지터 패턴 (객체 구조와 연산을 분리)
 * 객체 구조를 변경하지 않고 새로운 연산을 추가할 수 있게 해주는 패턴
 * 데이터 구조와 처리를 분리하여 처리 방식만 쉽게 확장할 수 있음
 */
public interface Investment {

    void accept(InvestmentVisitor visitor);

}
