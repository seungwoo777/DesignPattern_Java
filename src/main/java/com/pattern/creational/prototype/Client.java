package com.pattern.creational.prototype;

/**
 * 프로토타입 패턴 클라이언트
 * 기존 투자 상품을 복제하여 여러 투자 상품을 생성하고 복제본이 원본과 독립적인 객체인지 확인
 */
public class Client {

    public static void main(String[] args) {
        // 정기예금 원본 생성
        System.out.println("=== 예금 상품 복제 ===");
        SavingsProduct originalSavings = new SavingsProduct();
        System.out.println("원본: " + originalSavings);

        InvestmentProduct savings1 = originalSavings.clone(); // 원본 예금 상품을 복제하여 투자 조건이 다른 상품 생성
        savings1.setInvestmentAmount(20_000_000L); // 복제한 예금 상품마다 투자 금액과 수익률 변경
        savings1.setExpectedReturnRate(20.0);      // 복제한 예금 상품마다 투자 금액과 수익률 변경

        InvestmentProduct savings2 = originalSavings.clone(); // 원본 예금 상품을 복제하여 투자 조건이 다른 상품 생성
        savings2.setInvestmentAmount(30_000_000L); // 복제한 예금 상품마다 투자 금액과 수익률 변경
        savings2.setExpectedReturnRate(4.0);       // 복제한 예금 상품마다 투자 금액과 수익률 변경

        InvestmentProduct savings3 = originalSavings.clone(); // 원본 예금 상품을 복제하여 투자 조건이 다른 상품 생성
        savings3.setInvestmentAmount(50_000_000L); // 복제한 예금 상품마다 투자 금액과 수익률 변경
        savings3.setExpectedReturnRate(4.2);       // 복제한 예금 상품마다 투자 금액과 수익률 변경

        // 복제한 예금 상품 정보 출력
        savings1.getInfo();
        savings2.getInfo();
        savings3.getInfo();

        System.out.println();

        // 주식 투자 상품 원본 생성 및 복제
        System.out.println("=== 주식 투자 상품 복제 ===");
        StockInvestment originalStock = new StockInvestment();
        System.out.println("원본: " + originalStock);

        InvestmentProduct clonedStock = originalStock.clone();
        clonedStock.setInvestmentAmount(20_000_000L);   // 복제본의 투자 조건을 변경하여 원본과 독립적인지 확인
        clonedStock.setExpectedReturnRate(15.0);        // 복제본의 투자 조건을 변경하여 원본과 독립적인지 확인
        clonedStock.setName("삼성전자 주식 - 추가 투자");  // 복제본의 투자 조건을 변경하여 원본과 독립적인지 확인

        System.out.println("원본 주식 투자금: " + originalStock.getInvestmentAmount());
        System.out.println("복제 주식 투자금: " + clonedStock.getInvestmentAmount());
        System.out.println("원본과 복제본이 다른 객체? " + (originalStock != clonedStock));

        // 원본 및 복제 상품 정보 출력
        originalStock.getInfo();
        clonedStock.getInfo();
    }
}
