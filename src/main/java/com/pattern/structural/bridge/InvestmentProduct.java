package com.pattern.structural.bridge;

import lombok.AllArgsConstructor;

/**
 * 브릿지 패턴 (Bridge Pattern)
 * 추상화(투자 상품)와 구현(투자 전략)을 분리하여 독립적으로 확장할 수 있게 하는 패턴
 * 투자 상품 종류와 투자 전략을 자유롭게 조합
 * 이를 통해 조합별 클래스 무수히 생성하는 폭발 문제를 방지하고, 런타임에 구현을 교체할 수 있다.
 */
@AllArgsConstructor
public abstract class InvestmentProduct {

    // 투자 전략 구현체를 참조 - 투자 상품은 구체적인 투자 전략에 의존하지 않고 InvestmentStrategy 인터페이스에 의존
    protected final InvestmentStrategy investmentStrategy;

    // 투자 실행 행위 - 각 투자 상품에서 구체적인 투자 방식을 구현
    public abstract void invest();

    // 현재 적용된 투자 전략 정보를 반환 - 투자 상품은 전략의 구체적인 구현을 몰라도 인터페이스를 통해 전략 정보를 전달
    public String strategyInfo() {
        return investmentStrategy.apply();
    }

}
