package com.pattern.behavioral.strategy;

public class Client {

    public static void main(String[] args) {
        Investor investor = new Investor("투자자", new ValueInvestment());

        // 가치 투자
        investor.executeInvestment("삼성전자");

        // 전략 변경 → 성장주 투자
        investor.setStrategy(new GrowthInvestment());
        investor.executeInvestment("엔비디아");

        // 전략 변경 → 배당 투자
        investor.setStrategy(new DividendInvestment());
        investor.executeInvestment("SK하이닉스");
    }

}
