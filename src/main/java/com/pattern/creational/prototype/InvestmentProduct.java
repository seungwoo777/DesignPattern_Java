package com.pattern.creational.prototype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 프로토타입 패턴 (Prototype Pattern)
 * 기존 객체를 복제(clone)하여 새로운 객체를 생성하는 패턴 - 객체 생성 비용이 크거나 비슷한 객체를 대량으로 만들어야 할 때 유용
 * 금융 상품을 기존 상품 객체로부터 복제하여 투자 조건이 다른 여러 상품을 빠르게 생성하는 상황을 표현
 */
@Getter
@Setter
@ToString
public abstract class InvestmentProduct implements Cloneable {

    protected String name;               // 투자 상품 이름
    protected long investmentAmount;     // 투자 금액
    protected double expectedReturnRate; // 예상 수익률 (%)

    // 투자 상품 정보를 출력 - 하위 클래스에서 구체적으로 구현
    public abstract void getInfo();

    /**
     * 깊은 복사(deep copy)를 수행(복제)
     * Object의 clone()을 사용하여 객체 자체를 복제
     * 현재 필드들은 String과 기본 자료형이므로 별도의 깊은 복사가 필요 X
     */
    @Override
    public InvestmentProduct clone() {
        try {
            return (InvestmentProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("투자 상품 복제에 실패", e);
        }
    }

}
