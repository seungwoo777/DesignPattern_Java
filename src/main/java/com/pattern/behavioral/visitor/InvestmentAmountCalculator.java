package com.pattern.behavioral.visitor;

import lombok.Getter;

/**
 * 총 투자 원금 계산기
 */
public class InvestmentAmountCalculator implements InvestmentVisitor {

    @Getter
    private int totalAmount = 0;

    @Override
    public void visit(StockInvestment stock) {
        totalAmount += stock.getPrincipal();
    }

    @Override
    public void visit(BondInvestment bond) {
        totalAmount += bond.getPrincipal();
    }

    @Override
    public void visit(DepositInvestment deposit) {
        totalAmount += deposit.getPrincipal();
    }

}
