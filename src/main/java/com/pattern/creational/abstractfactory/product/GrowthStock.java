package com.pattern.creational.abstractfactory.product;

/**
 * 성장주 투자 상품 클래스
 * 공격적인 투자 성향에 적합한 성장주 상품을 구현 - 높은 성장 가능성을 추구하지만 변동성이 클 수 있음
 */
public class GrowthStock implements InvestmentAsset  {

    @Override
    public void invest() {
        System.out.println("성장주 투자 실행! (기대수익률: 높음, 변동성: 높음)");
    }

}
