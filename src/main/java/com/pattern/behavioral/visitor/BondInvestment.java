package com.pattern.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 채권 투자
 */
@Getter
@AllArgsConstructor
public class BondInvestment implements Investment {

    private final String name;
    private final int interestIncome;
    private final int principal;

    @Override
    public void accept(InvestmentVisitor visitor) {
        visitor.visit(this);
    }

}
