package com.pattern.behavioral.strategy;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * 투자자
 */
@AllArgsConstructor
public class Investor {

    private final String name;
    @Setter
    private InvestmentStrategy strategy;

    public void executeInvestment(String stockName) {
        System.out.print("[" + name + "] ");
        strategy.invest(stockName);
    }

}
