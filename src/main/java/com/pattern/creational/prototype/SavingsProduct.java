package com.pattern.creational.prototype;

/**
 * 예금 상품 클래스 - InvestmentProduct를 상속받아 예금 상품의 기본 정보를 구성
 */
public class SavingsProduct extends InvestmentProduct {

    public SavingsProduct() {
        this.name = "정기예금";
        this.investmentAmount = 10_000_000L;
        this.expectedReturnRate = 3.5;
    }

    // 예금 상품 정보를 출력
    @Override
    public void getInfo() {
        System.out.println("[예금 상품] 상품명: " + name + ", 투자금: " + investmentAmount + "원, 예상 수익률: " + expectedReturnRate + "% - 안정적인 이자 수익을 추구하는 예금 상품");
    }
}
