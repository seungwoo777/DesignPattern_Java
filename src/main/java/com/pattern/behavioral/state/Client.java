package com.pattern.behavioral.state;

public class Client {

    public static void main(String[] args) {
        InvestmentProduct product = new InvestmentProduct("국채 ETF", 50);
        // 판매 준비 상태
        product.withdraw();
        product.invest();       // → 거래 활성 상태 전환
        product.invest();

        product.withdraw();     // → 거래 중에는 회수 불가

        // 손실 반영 → 상품 가치 소진 → 거래 종료 상태
        product.applyLoss(30);
        product.applyLoss(30);  // 가치 <= 0 → 거래 종료 상태

        // 거래 종료 상태
        product.invest();
        product.applyLoss(10);
        product.withdraw();
    }

}
