package com.pattern.structural.decorator;

/**
 * 복리 효과 추가
 */
public class CompoundInterestDecorator extends InvestmentDecorator {

    public CompoundInterestDecorator(Investment investment) {
        super(investment);
    }

    @Override
    public String getDescription() {
        return investment.getDescription() + " + 복리 효과";
    }

    @Override
    public int getReturn() {
        return investment.getReturn() + 8;
    }

}
