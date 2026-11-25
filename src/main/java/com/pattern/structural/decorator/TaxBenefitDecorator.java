package com.pattern.structural.decorator;

/**
 * 세제 혜택 추가
 */
public class TaxBenefitDecorator extends InvestmentDecorator {

    public TaxBenefitDecorator(Investment investment) {
        super(investment);
    }

    @Override
    public String getDescription() {
        return investment.getDescription() + " + 세제 혜택";
    }

    @Override
    public int getReturn() {
        return investment.getReturn() + 10;
    }

}
