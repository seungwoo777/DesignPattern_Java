package com.pattern.behavioral.interpreter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VariableExpression implements Expression {

    private final String name;

    @Override
    public int interpret(FinancialContext context) {
        return context.get(name);
    }

}
