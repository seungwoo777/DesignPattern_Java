package com.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberExpression implements Expression {

    private final int value;

    @Override
    public int interpret(FinancialContext  context) {
        return value;
    }

}
