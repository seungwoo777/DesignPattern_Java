package com.pattern.behavioral.strategy;

/**
 * 성장주 투자 전략
 */
public class GrowthInvestment implements InvestmentStrategy {

    @Override
    public void invest(String stockName) {
        System.out.println(stockName + "에 성장주 투자 (미래 성장 가능성 중심 분석)");
    }

}
