package com.pattern.behavioral.templatemethod;

public class RealEstateInvestment extends InvestmentTemplate {

    @Override
    protected void analyzeMarket() {
        System.out.println("입지, 거래량, 임대 수요를 분석");
    }

    @Override
    protected void executeInvestment(String assetName) {
        System.out.println(assetName + " 부동산을 매입 (임대 수익 확보)");
    }

    @Override
    protected void manageRisk() {
        System.out.println("공실률과 대출 상환 부담을 점검");
    }

}
