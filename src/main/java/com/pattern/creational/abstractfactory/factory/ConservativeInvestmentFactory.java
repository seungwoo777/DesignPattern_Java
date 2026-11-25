package com.pattern.creational.abstractfactory.factory;

import com.pattern.creational.abstractfactory.product.BondInvestment;
import com.pattern.creational.abstractfactory.risk.BondRiskManagement;
import com.pattern.creational.abstractfactory.product.InvestmentAsset;
import com.pattern.creational.abstractfactory.risk.RiskManagementProduct;

/**
 * 안정형 투자 팩토리
 * 안정적인 투자 성향에 적합한 투자 상품과 위험 관리 상품을 함께 생성
 * 생성하는 상품 패밀리:
 * - BondInvestment
 * - BondRiskManagement
 */
public class ConservativeInvestmentFactory implements InvestmentProductFactory {

    // 안정형 투자에 적합한 채권 상품을 생성
    @Override
    public InvestmentAsset createInvestmentAsset() {
        return new BondInvestment();
    }

    // 안정형 투자에 적합한 채권 위험 관리 상품을 생성
    @Override
    public RiskManagementProduct createRiskManagementProduct() {
        return new BondRiskManagement();
    }

}
