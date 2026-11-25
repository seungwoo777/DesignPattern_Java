package com.pattern.creational.abstractfactory.product;

/**
 * 채권 투자 상품 클래스
 * 안정적인 투자 성향에 적합한 채권 상품을 구현 - 일반적으로 주식보다 변동성이 낮은 상품을 지향
 */
public class BondInvestment implements InvestmentAsset {

    // 채권에 투자하는 동작을 구현
    @Override
    public void invest() {
        System.out.println("채권 투자 실행! (기대수익률: 안정적, 변동성: 낮음)");
    }

}
