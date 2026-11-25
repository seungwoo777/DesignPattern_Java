package com.pattern.structural.bridge;

/**
 * 주식 투자 상품
 * InvestmentProduct를 상속받아 주식 투자 행위를 정의
 * 투자 전략은 상위 클래스에서 전달받아 사용
 */
public class StockInvestment extends InvestmentProduct {

    // 주식 투자 상품 생성 - 전달받은 투자 전략을 상위 클래스에 주입
    public StockInvestment(InvestmentStrategy investmentStrategy) {
        super(investmentStrategy);
    }

    @Override
    public void invest() {
        System.out.println("주식 매수 및 투자 실행 " + strategyInfo());
    }

}
