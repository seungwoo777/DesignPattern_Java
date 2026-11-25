package com.pattern.creational.factorymethod.factory;

import com.pattern.creational.factorymethod.product.Bond;
import com.pattern.creational.factorymethod.product.FinancialProduct;

/**
 * 채권 상품 팩토리 클래스
 * Bond 객체를 생성하는 책임을 가지고 실제 채권 상품 생성 로직을 구현
 */
public class BondFactory extends FinancialProductFactory  {

    // 채권 상품 생성
    @Override
    public FinancialProduct createProduct(String name, int investmentAmount) {
        return new Bond(name, investmentAmount);
    }

}
