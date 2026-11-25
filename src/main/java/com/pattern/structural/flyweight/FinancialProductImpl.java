package com.pattern.structural.flyweight;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinancialProductImpl implements FinancialProduct {

    private final String name;
    private final String riskType;

    @Override
    public void display(int accountId, int quantity) {
        System.out.println(name + "(" + riskType + ") 금융 상품을 " + accountId + "번 계좌에서 " + quantity + "개 보유");
    }

}
