package com.pattern.creational.abstractfactory.factory;

import com.pattern.creational.abstractfactory.product.InvestmentAsset;
import com.pattern.creational.abstractfactory.risk.RiskManagementProduct;

/**
 * 추상 팩토리 패턴 (Abstract Factory Pattern)
 * 관련된 객체들의 그룹(패밀리)을 생성하기 위한 인터페이스를 제공하는 패턴
 * 구체적인 클래스를 지정하지 않고도, 서로 관련 있는 객체들을 일관성 있게 생성할 수 있음.
 *
 * 서로 연관된 금융 상품군을 생성하기 위한 인터페이스를 제공
 * 투자 성향별로 서로 다른 투자 상품과 위험 관리 상품을 생성
 * 클라이언트는 구체적인 상품 클래스에 의존하지 않고 이 팩토리 인터페이스를 통해 상품을 생성
 */
public interface InvestmentProductFactory {

    // 투자 상품을 생성
    InvestmentAsset createInvestmentAsset();

    // 위험 관리 상품을 생성
    RiskManagementProduct createRiskManagementProduct();

}
