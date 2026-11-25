package com.pattern.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasicInvestment implements Investment {

    private final String name;

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public int getReturn() {
        return 10;
    }

}
