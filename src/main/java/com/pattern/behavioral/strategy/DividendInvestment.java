package com.pattern.behavioral.strategy;

/**
 * 배당 투자 전략
 */
public class DividendInvestment implements InvestmentStrategy {

    @Override
    public void invest(String stockName) {
        System.out.println(stockName + "에 배당 투자 (배당 수익 중심 투자)");
    }

}
