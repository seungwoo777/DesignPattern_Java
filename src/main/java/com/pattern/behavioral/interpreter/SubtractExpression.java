package com.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubtractExpression implements Expression {

    private final Expression left;
    private final Expression right;

    @Override
    public int interpret(FinancialContext context) {
        return left.interpret(context) - right.interpret(context);
    }

}
