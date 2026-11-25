package com.pattern.creational.factorymethod.product;

/**
 * 채권 상품 클래스 - FinancialProduct를 상속받아 채권 투자 상품을 구현
 */
public class Bond extends FinancialProduct {

    // 채권 상품 생성자
    public Bond(String name, int investmentAmount) {
        super(name, investmentAmount);
    }

    // 채권 투자 실행
    @Override
    public void invest() {
        System.out.println(name + " 채권에 투자 (투자금액: " + investmentAmount + "원)");
    }

}
