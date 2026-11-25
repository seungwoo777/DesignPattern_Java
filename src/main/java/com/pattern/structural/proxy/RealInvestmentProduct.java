package com.pattern.structural.proxy;


public class RealInvestmentProduct implements InvestmentProduct {

    private final String name;
    private final int expectedReturn;

    public RealInvestmentProduct(String name, int expectedReturn) {
        this.name = name;
        this.expectedReturn = expectedReturn;
        System.out.println("[" + name + "] 실제 투자 상품 객체 생성 완료");
    }

    @Override
    public void subscribe(String investorName, int investorGrade) {
        System.out.println(investorName + " 투자자가 " + name + " 상품에 가입 - 예상 수익률: " + expectedReturn + "%");
    }
}
