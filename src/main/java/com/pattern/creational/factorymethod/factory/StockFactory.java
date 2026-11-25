package com.pattern.creational.factorymethod.factory;

import com.pattern.creational.factorymethod.product.FinancialProduct;
import com.pattern.creational.factorymethod.product.Stock;

/**
 * 주식 상품 팩토리 클래스
 * Stock 객체를 생성하는 책임을 가짐. -  실제 주식 상품 생성 로직을 구현
 */
public class StockFactory extends FinancialProductFactory  {

    // 주식 상품 생성
    @Override
    public FinancialProduct createProduct(String name, int investmentAmount) {
        return new Stock(name, investmentAmount);
    }

}
