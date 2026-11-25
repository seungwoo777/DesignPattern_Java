package com.pattern.structural.facade;

public class Client {

    public static void main(String[] args) {
        // 퍼사드를 통해 한 줄로 투자 서비스 시작
        InvestmentFacade investment = new InvestmentFacade();
        investment.startInvestment("seungwoo", "삼성전자", "KOSPI");
    }

}
