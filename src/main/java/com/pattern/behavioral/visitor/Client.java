package com.pattern.behavioral.visitor;

import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Investment> investments = List.of(
                new StockInvestment("삼성전자", 500, 10000),
                new BondInvestment("국채", 300, 5000),
                new DepositInvestment("정기예금", 200, 3000)
        );

        // 예상 수익 계산
        ReturnCalculator returnCalc = new ReturnCalculator();
        for (Investment investment : investments) {
            investment.accept(returnCalc);
        }
        System.out.println("총 예상 수익: " + returnCalc.getTotalReturn() + "원");

        // 총 투자 원금 계산
        InvestmentAmountCalculator amountCalc = new InvestmentAmountCalculator();
        for (Investment investment : investments) {
            investment.accept(amountCalc);
        }
        System.out.println("총 투자 원금: " + amountCalc.getTotalAmount() + "원");
    }

}
