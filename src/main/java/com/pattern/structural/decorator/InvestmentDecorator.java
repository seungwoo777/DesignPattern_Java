package com.pattern.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class InvestmentDecorator implements Investment {

    protected final Investment investment;

}
