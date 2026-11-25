package com.pattern.creational.builder;

import lombok.ToString;

/**
 * 빌더 패턴 (Builder Pattern)
 * 복잡한 객체의 생성 과정을 단계별로 분리하여, 동일한 생성 절차로 다양한 객체를 만들 수 있는 패턴
 * 생성자에 매개변수가 많을 때 가독성을 높이고, 불변(immutable) 객체를 쉽게 만들 수 있음
 * 투자 상품처럼 여러 속성을 조합하여 객체를 생성해야 하는 경우에 적합
 */
@ToString
public class InvestmentProduct {

    private final String name;               // 투자 상품명
    private final String productType;        // 투자 상품 유형
    private final double expectedReturnRate; // 기대수익률 (%)
    private final RiskLevel riskLevel;       // 투자 위험 수준
    private final long minInvestmentAmount;  // 최소 투자금액 (원)
    private final double managementFee;      // 운용 수수료 (%)

    private InvestmentProduct(Builder builder) {
        this.name = builder.name;
        this.productType = builder.productType;
        this.expectedReturnRate = builder.expectedReturnRate;
        this.riskLevel = builder.riskLevel;
        this.minInvestmentAmount = builder.minInvestmentAmount;
        this.managementFee = builder.managementFee;
    }

    // 내부 정적 빌더 클래스 - Fluent API를 사용하여 메서드 체이닝으로 투자 상품을 생성
    public static class Builder {
        // Mandatory
        private final String name;
        private final String productType;

        // Optional(Default Value Set)
        private double expectedReturnRate = 0.0;
        private RiskLevel riskLevel = RiskLevel.MEDIUM;
        private long minInvestmentAmount = 100000;
        private double managementFee = 1.0;


        // 필수 필드는 생성자에서 받음
        public Builder(String name, String productType) {
            this.name = name;
            this.productType = productType;
        }

        // 기대수익률 설정
        public Builder expectedReturnRate(double expectedReturnRate) {
            this.expectedReturnRate = expectedReturnRate;
            return this;
        }

        // 투자 위험 수준 설정
        public Builder riskLevel(RiskLevel riskLevel) {
            this.riskLevel = riskLevel;
            return this;
        }

        // 최소 투자금액 설정
        public Builder minInvestmentAmount(long minInvestmentAmount) {
            this.minInvestmentAmount = minInvestmentAmount;
            return this;
        }

        // 운용 수수료 설정
        public Builder managementFee(double managementFee) {
            this.managementFee = managementFee;
            return this;
        }

        // 최종적으로 InvestmentProduct 객체를 생성
        public InvestmentProduct build() {
            return new InvestmentProduct(this);
        }
    }

}
