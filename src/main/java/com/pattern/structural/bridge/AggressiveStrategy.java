package com.pattern.structural.bridge;

/**
 * 공격적 투자 전략 구현
 */
public class AggressiveStrategy implements InvestmentStrategy {

    @Override
    public String apply() {
        return "공격적 투자 전략 적용 (고수익 · 고위험)";
    }

}
