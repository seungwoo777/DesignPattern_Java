package com.pattern.structural.bridge;

/**
 * 보수적 투자 전략 구현
 */
public class ConservativeStrategy implements InvestmentStrategy {

    @Override
    public String apply() {
        return "보수적 투자 전략 적용 (안정성 · 저위험)";
    }

}
