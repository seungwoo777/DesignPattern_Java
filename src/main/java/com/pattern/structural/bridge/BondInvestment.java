package com.pattern.structural.bridge;

/**
 * 채권 투자 상품 구현
 * InvestmentProduct를 상속받아 채권 투자 행위를 정의
 * 투자 전략은 상위 클래스에서 전달받아 사용
 */
public class BondInvestment extends InvestmentProduct {

    // 채권 투자 상품 생성 - 전달받은 투자 전략을 상위 클래스에 주입
    public BondInvestment(InvestmentStrategy investmentStrategy) {
        super(investmentStrategy);
    }

    @Override
    public void invest() {
        System.out.println("채권 매수 및 투자 실행 " + strategyInfo());
    }

}
