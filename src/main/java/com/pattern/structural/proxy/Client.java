package com.pattern.structural.proxy;

public class Client {

    public static void main(String[] args) {
        int investorGrade = 10;
        System.out.println("투자자 등급: " + investorGrade + "\n");

        // 필요 등급 5 상품 → 가입 성공
        InvestmentProduct lowRiskProduct = new InvestmentProductProxy("국채 안정형 펀드", 3, 5);
        System.out.println("--- 국채 안정형 펀드 가입 시도 ---");
        lowRiskProduct.subscribe("홍길동", investorGrade);

        System.out.println();

        // 필요 등급 20 상품 → 가입 실패
        InvestmentProduct highRiskProduct = new InvestmentProductProxy("고위험 헤지펀드", 25, 20);
        System.out.println("--- 고위험 헤지펀드 가입 시도 ---");
        highRiskProduct.subscribe("홍길동", investorGrade);

    }

}
