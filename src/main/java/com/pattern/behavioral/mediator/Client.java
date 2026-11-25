package com.pattern.behavioral.mediator;

public class Client {

    public static void main(String[] args) {
        MarketMediator financialMarket = new FinancialMarket();
        MarketParticipant investor = new Investor("A", financialMarket);
        MarketParticipant analyst1 = new Analyst("B", financialMarket);
        MarketParticipant analyst2 = new Analyst("C", financialMarket);

        financialMarket.addParticipant(investor);
        financialMarket.addParticipant(analyst1);
        financialMarket.addParticipant(analyst2);

        System.out.println("=== 금융시장 정보 공유 ===");
        investor.publish("금리 인하 기대감에 주식 매수 시작");

        System.out.println();
        analyst1.publish("기업 실적 분석 완료");
    }

}
