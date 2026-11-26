package com.pattern.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주식 투자
 */
@Getter
@AllArgsConstructor
public class StockInvestment implements Investment {

    private final String name;
    private final int expectedReturn;
    private final int principal;

    @Override
    public void accept(InvestmentVisitor visitor) {
        visitor.visit(this);
    }

}
