package com.pattern.creational.builder;

/**
 * 빌더 패턴 클라이언트
 * 주식형 투자 상품과 채권형 투자 상품을 빌더 패턴을 이용하여 생성
 */
public class Client {

    public static void main(String[] args) {
        // 주식형 투자 상품 생성
        InvestmentProduct stockProduct = new InvestmentProduct.Builder("K-성장주 펀드", "주식형")
                .expectedReturnRate(12.5)
                .riskLevel(RiskLevel.HIGH)
                .minInvestmentAmount(1000000)
                .managementFee(1.2)
                .build();

        // 채권형 투자 상품 생성
        InvestmentProduct bondProduct = new InvestmentProduct.Builder("K-안정채권 펀드", "채권형")
                .expectedReturnRate(4.5)
                .riskLevel(RiskLevel.LOW)
                .minInvestmentAmount(500000)
                .managementFee(0.5)
                .build();

        // 혼합형 투자 상품 생성
        InvestmentProduct mixedProduct = new InvestmentProduct.Builder("K-균형투자 펀드", "혼합형")
                .expectedReturnRate(8.0)
                .riskLevel(RiskLevel.MEDIUM)
                .minInvestmentAmount(300000)
                .build();

        System.out.println("=== 투자 상품 생성 완료 ===");
        System.out.println(stockProduct);
        System.out.println(bondProduct);
        System.out.println(mixedProduct);
    }

}
