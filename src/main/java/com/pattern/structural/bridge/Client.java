package com.pattern.structural.bridge;

/**
 * 브릿지 패턴 데모 - 투자 상품 + 투자 전략 조합
 * 주식과 채권에 공격적 또는 보수적 전략을 조합하여 투자 상품과 투자 전략이 독립적으로 확장되는 구조를 확인
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("=== 브릿지 패턴: 투자 상품 + 투자 전략 조합 ===\n");

        // 주식 + 공격적 투자 전략
        InvestmentProduct aggressiveStock = new StockInvestment(new AggressiveStrategy());
        aggressiveStock.invest();

        // 주식 + 보수적 투자 전략
        InvestmentProduct conservativeStock = new StockInvestment(new ConservativeStrategy());
        conservativeStock.invest();

        // 채권 + 공격적 투자 전략
        InvestmentProduct aggressiveBond = new BondInvestment(new AggressiveStrategy());
        aggressiveBond.invest();

        // 채권 + 보수적 투자 전략
        InvestmentProduct conservativeBond = new BondInvestment(new ConservativeStrategy());
        conservativeBond.invest();

    }

}
