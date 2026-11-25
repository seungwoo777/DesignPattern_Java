package com.pattern.creational.factorymethod.product;

/**
 * 주식 상품 클래스 - FinancialProduct를 상속받아 주식 투자 상품을 구현
 */
public class Stock extends FinancialProduct {

    // 주식 상품 생성자
    public Stock(String name, int investmentAmount) {
        super(name, investmentAmount);
    }

    // 주식 투자 실행
    @Override
    public void invest() {
        System.out.println(name + " 주식에 투자 (투자금액: " + investmentAmount + "원)");
    }

}
