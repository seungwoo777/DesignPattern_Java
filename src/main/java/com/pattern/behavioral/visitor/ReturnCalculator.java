package com.pattern.behavioral.visitor;

import lombok.Getter;

/**
 * 예상 수익 계산기
 */
public class ReturnCalculator implements InvestmentVisitor {

    @Getter
    private int totalReturn = 0;

    @Override
    public void visit(StockInvestment stock) {
        totalReturn += stock.getExpectedReturn();
    }

    @Override
    public void visit(BondInvestment bond) {
        totalReturn += bond.getInterestIncome();
    }

    @Override
    public void visit(DepositInvestment deposit) {
        totalReturn += deposit.getInterestIncome();
    }

}
