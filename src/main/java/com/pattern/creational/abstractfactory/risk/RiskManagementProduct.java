package com.pattern.creational.abstractfactory.risk;

/**
 * 위험 관리 상품 인터페이스
 * 각 투자 패키지에 맞는 위험 관리 전략을 제공 - 투자 성향에 따라 위험 관리 방식이 달라짐
 */
public interface RiskManagementProduct {

    // 투자 위험을 관리
    void manageRisk();

}
