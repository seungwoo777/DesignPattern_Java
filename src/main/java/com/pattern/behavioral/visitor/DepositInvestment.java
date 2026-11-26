package com.pattern.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepositInvestment implements Investment {

    private final String name;
    private final int interestIncome;
    private final int principal;

    @Override
    public void accept(InvestmentVisitor visitor) {
        visitor.visit(this);
    }

}
