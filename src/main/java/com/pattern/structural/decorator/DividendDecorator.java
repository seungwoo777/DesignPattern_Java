package com.pattern.structural.decorator;

/**
 * 배당 수익 추가
 */
public class DividendDecorator extends InvestmentDecorator {

    public DividendDecorator(Investment investment) {
        super(investment);
    }

    @Override
    public String getDescription() {
        return investment.getDescription() + " + 배당 수익";
    }

    @Override
    public int getReturn() {
        return investment.getReturn() + 15;
    }

}
