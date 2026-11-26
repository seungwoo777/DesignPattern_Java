package com.pattern.behavioral.templatemethod;

public class Client {

    public static void main(String[] args) {
        System.out.println("=== 주식 투자 ===");
        InvestmentTemplate stockInvestment = new StockInvestment();
        stockInvestment.invest("삼성전자");

        System.out.println("=== 채권 투자 ===");
        InvestmentTemplate bondInvestment = new BondInvestment();
        bondInvestment.invest("국채");

        System.out.println("=== 부동산 투자 ===");
        InvestmentTemplate realEstateInvestment = new RealEstateInvestment();
        realEstateInvestment.invest("강남 아파트");
    }

}
