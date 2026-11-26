package com.pattern.behavioral.templatemethod;

public class BondInvestment extends InvestmentTemplate {

    @Override
    protected void analyzeMarket() {
        System.out.println("금리 추세와 채권 발행자의 신용도를 분석");
    }

    @Override
    protected void executeInvestment(String assetName) {
        System.out.println(assetName + " 채권을 매수 (만기 수익률 확보)");
    }

    @Override
    protected void manageRisk() {
        System.out.println("금리 변동과 신용 위험을 점검");
    }

}
