package com.pattern.behavioral.templatemethod;

public class StockInvestment extends InvestmentTemplate {

    @Override
    protected void analyzeMarket() {
        System.out.println("주가 추세와 기업 실적을 분석");
    }

    @Override
    protected void executeInvestment(String assetName) {
        System.out.println(assetName + " 주식을 매수 (100주 매수)");
    }

    @Override
    protected void manageRisk() {
        System.out.println("손절 가격을 설정하고 변동성을 점검...");
    }

}
