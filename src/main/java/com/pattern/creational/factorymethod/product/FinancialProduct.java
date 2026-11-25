package com.pattern.creational.factorymethod.product;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 팩토리 메서드 패턴 - 금융 상품 추상 클래스
 * 객체 생성을 서브클래스에 위임하여, 어떤 클래스의 인스턴스를 만들지를 서브클래스가 결정하게 하는 패턴
 * 모든 금융 상품이 공통적으로 가지는 속성을 정의
 * 상품명과 투자금액을 관리하며 상품별 투자 방식은 하위 클래스에서 구현
 */
@ToString
@AllArgsConstructor
public abstract class FinancialProduct {

    // 금융 상품 이름
    protected String name;

    // 투자 금액
    protected int investmentAmount;

    // 금융 상품 투자 실행 - 각 금융 상품의 구체적인 투자 방식은 하위 클래스에서 구현한다.
    public abstract void invest();

}
