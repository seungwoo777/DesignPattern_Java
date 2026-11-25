package com.pattern.structural.proxy;


public class InvestmentProductProxy implements InvestmentProduct  {

    private final String productName;
    private final int expectedReturn;
    private final int requiredGrade;

    private RealInvestmentProduct realInvestmentProduct;

    public InvestmentProductProxy(String productName, int expectedReturn, int requiredGrade) {
        this.productName = productName;
        this.expectedReturn = expectedReturn;
        this.requiredGrade = requiredGrade;
    }

    @Override
    public void subscribe(String investorName, int investorGrade) {
        // 투자자 등급 검증
        if (investorGrade < requiredGrade) {
            System.out.println("가입 조건을 충족하지 못했습니다! (필요 등급: " + requiredGrade + ", 현재 등급: " + investorGrade + ")");
            return;
        }

        // 지연 초기화: 실제 가입 요청이 승인된 경우에만 상품 객체 생성
        if (realInvestmentProduct == null) {
            realInvestmentProduct = new RealInvestmentProduct(productName, expectedReturn);
        }

        realInvestmentProduct.subscribe(investorName, investorGrade);
    }
}
