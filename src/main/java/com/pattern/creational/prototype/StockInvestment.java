package com.pattern.creational.prototype;

/**
 * 주식 투자 상품 클래스 - InvestmentProduct를 상속받아 주식 투자 상품의 기본 정보를 구성
 */
public class StockInvestment extends InvestmentProduct {

    public StockInvestment() {
        this.name = "삼성전자 주식";
        this.investmentAmount = 5_000_000L;
        this.expectedReturnRate = 8.0;
    }

    // 주식 투자 상품 정보를 출력
    @Override
    public void getInfo() {
        System.out.println("[주식 투자] 상품명: " + name + ", 투자금: " + investmentAmount +  "원, 예상 수익률: " + expectedReturnRate + "% - 주가 변동에 따른 수익과 손실이 발생할 수 있는 투자 상품");
    }
}
