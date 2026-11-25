package com.pattern.creational.factorymethod;

import com.pattern.creational.factorymethod.factory.BondFactory;
import com.pattern.creational.factorymethod.factory.FinancialProductFactory;
import com.pattern.creational.factorymethod.factory.StockFactory;
import com.pattern.creational.factorymethod.product.FinancialProduct;

/**
 * 팩토리 메서드 패턴 클라이언트
 * 각 팩토리를 통해 금융 상품을 생성하고 투자
 */
public class Client {

    public static void main(String[] args) {
        // 주식 팩토리를 통해 주식 상품 생성
        FinancialProductFactory stockFactory = new StockFactory();
        FinancialProduct stock = stockFactory.order("삼성전자 주식", 5000000);
        stock.invest();

        System.out.println();

        // 채권 팩토리를 통해 채권 상품 생성
        FinancialProductFactory bondFactory = new BondFactory();
        FinancialProduct bond = bondFactory.order("국채 10년물", 10000000);
        bond.invest();
    }

}
