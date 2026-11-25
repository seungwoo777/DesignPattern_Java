package com.pattern.structural.bridge;

/**
 * 투자 전략 인터페이스 - 구현부 역할
 * 투자 상품과 독립적으로 투자 전략을 정의
 * 각각의 투자 상품은 이 인터페이스를 통해 서로 다른 투자 전략을 적용
 */
public interface InvestmentStrategy {

    String apply();

}
