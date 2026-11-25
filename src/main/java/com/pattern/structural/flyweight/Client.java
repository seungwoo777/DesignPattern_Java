package com.pattern.structural.flyweight;

public class Client {

    public static void main(String[] args) {
        FinancialProductFactory factory = new FinancialProductFactory();

        // 동일한 금융 상품 정보는 공유됨
        FinancialProduct bond1 = factory.getProduct("국채", "안정형");
        bond1.display(1000, 100);

        FinancialProduct bond2 = factory.getProduct("국채", "안정형");
        bond2.display(2000, 200);

        FinancialProduct stock1 = factory.getProduct("주식", "공격형");
        stock1.display(3000, 50);

        FinancialProduct stock2 = factory.getProduct("주식", "공격형");
        stock2.display(4000, 150);

        FinancialProduct bond3 = factory.getProduct("국채", "안정형");
        bond3.display(5000, 300);

        System.out.println("\n총 금융 상품 요청: 5회");
        System.out.println("실제 생성된 금융 상품 객체: " + factory.getCacheSize() + "개");
        System.out.println("같은 객체인지 확인: " + (bond1 == bond2));
    }
}
