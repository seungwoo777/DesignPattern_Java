package com.pattern.behavioral.strategy;

/**
 * 가치 투자 전략
 */
public class ValueInvestment implements InvestmentStrategy {

    @Override
    public void invest(String stockName) {
        System.out.println(stockName + "에 가치 투자 (저평가된 기업의 내재가치 분석)");
    }

}
