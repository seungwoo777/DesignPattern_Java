package com.pattern.creational.factorymethod.factory;

import com.pattern.creational.factorymethod.product.FinancialProduct;

/**
 * 금융 상품 팩토리 추상 클래스
 * 금융 상품의 생성 책임은 하위 팩토리에 위임하고 공통 생성 과정(order)은 부모 클래스에서 정의
 * 실제 금융 상품을 생성하는 createProduct()는 하위 클래스에서 구현
 */
public abstract class FinancialProductFactory {

    /**
     * 구체적인 금융 상품 생성은 하위 클래스에서 구현
     * @param name 금융 상품 이름
     * @param investmentAmount 투자 금액
     * @return 생성된 금융 상품
     */
    public abstract FinancialProduct createProduct(String name, int investmentAmount);

    /**
     * 금융 상품을 생성하고 생성 완료 메시지를 출력
     * 공통적인 생성 절차는 부모 클래스에서 처리하고 실제 객체 생성만 하위 클래스에 위임
     * @param name 금융 상품 이름
     * @param investmentAmount 투자 금액
     * @return 생성된 금융 상품
     */
    public FinancialProduct order(String name, int investmentAmount) {
        FinancialProduct product = createProduct(name, investmentAmount); // 하위 팩토리의 팩토리 메서드를 통해 상품 생성
        System.out.println("[" + name + "] 금융 상품이 생성되었습니다.");
        return product;
    }
}
