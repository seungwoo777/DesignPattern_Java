package com.pattern.behavioral.strategy;

/*
 * 전략 패턴 (알고리즘 캡슐화 및 교체)
 * 알고리즘을 인터페이스로 캡슐화하여 런타임에 교체 가능하게 하는 패턴
 * 조건문 없이 행동을 동적으로 변경할 수 있음
 */
public interface InvestmentStrategy {

    void invest(String stockName);

}
