package com.pattern.creational.abstractfactory.product;

/**
 * 투자 상품 인터페이스
 * 각 투자 상품은 투자 실행이라는 공통 기능을 제공 - 구체적인 투자 상품마다 투자 방식은 다르게 구현
 */
public interface InvestmentAsset {

    // 투자 상품에 투자
    void invest();

}
