package com.pattern.creational.abstractfactory;

import com.pattern.creational.abstractfactory.factory.AggressiveInvestmentFactory;
import com.pattern.creational.abstractfactory.factory.ConservativeInvestmentFactory;
import com.pattern.creational.abstractfactory.factory.InvestmentProductFactory;
import com.pattern.creational.abstractfactory.product.InvestmentAsset;
import com.pattern.creational.abstractfactory.risk.RiskManagementProduct;

/**
 * 추상 팩토리 패턴 클라이언트
 * 투자 성향별 금융 상품 패키지를 생성하고 사용
 * 클라이언트는 구체적인 투자 상품 클래스에 직접 의존하지 않음 - 팩토리 인터페이스를 통해 투자 상품과 위험 관리 상품을 생성
 */
public class Client {

    public static void main(String[] args) {
        // 공격형 투자 상품 패키지 생성
        System.out.println("=== 공격형 투자 패키지 ===");

        // 공격형 투자 팩토리
        InvestmentProductFactory aggressiveFactory = new AggressiveInvestmentFactory();

        InvestmentAsset aggressiveAsset = aggressiveFactory.createInvestmentAsset(); // [공격형 투자 팩토리]투자 상품을 생성
        aggressiveAsset.invest();  // [공격형 투자 팩토리]생성된 투자 상품을 사용

        RiskManagementProduct aggressiveRiskManagement = aggressiveFactory.createRiskManagementProduct(); // [공격형 투자 팩토리]위험 관리 상품을 생성
        aggressiveRiskManagement.manageRisk(); // [공격형 투자 팩토리]생성된 위험 관리 상품을 사용

        System.out.println();

        // 안정형 투자 상품 패키지 생성
        System.out.println("=== 안정형 투자 패키지 ===");

        // 안전형 투자 팩토리
        InvestmentProductFactory conservativeFactory = new ConservativeInvestmentFactory();

        InvestmentAsset conservativeAsset = conservativeFactory.createInvestmentAsset(); // [안전형 투자 팩토리]투자 상품을 생성
        conservativeAsset.invest(); // [안전형 투자 팩토리]생성된 투자 상품을 사용

        RiskManagementProduct conservativeRiskManagement = conservativeFactory.createRiskManagementProduct(); // [안전형 투자 팩토리]위험 관리 상품을 생성
        conservativeRiskManagement.manageRisk(); // [안전형 투자 팩토리]생성된 위험 관리 상품을 사용

    }
}
