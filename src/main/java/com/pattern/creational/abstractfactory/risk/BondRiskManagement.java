package com.pattern.creational.abstractfactory.risk;

/**
 * 채권 위험 관리 상품 클래스
 * 안정적인 투자 패키지에 적합한 위험 관리 전략을 구현 - 채권의 만기와 신용 위험 등을 고려하여 관리
 */
public class BondRiskManagement implements RiskManagementProduct {

    // 채권 투자 위험을 관리하는 동작을 구현
    @Override
    public void manageRisk() {
        System.out.println("채권 위험 관리! (만기 관리 및 신용 위험 관리)");
    }

}
