package com.pattern.creational.abstractfactory.risk;

/**
 * 주식 위험 관리 상품 클래스
 * 공격적인 투자 패키지에 적합한 위험 관리 전략을 구현 - 분산 투자 등을 통해 주식 투자 위험을 관리
 */
public class StockRiskManagement implements RiskManagementProduct {

    // 주식 투자 위험을 관리하는 동작을 구현한다.
    @Override
    public void manageRisk() {
        System.out.println("주식 포트폴리오 분산! (종목 분산 및 손실 위험 관리)");
    }

}
