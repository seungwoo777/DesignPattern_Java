package com.pattern.creational.abstractfactory.factory;

import com.pattern.creational.abstractfactory.product.GrowthStock;
import com.pattern.creational.abstractfactory.product.InvestmentAsset;
import com.pattern.creational.abstractfactory.risk.RiskManagementProduct;
import com.pattern.creational.abstractfactory.risk.StockRiskManagement;

/**
 * 공격형 투자 팩토리
 * 공격적인 투자 성향에 적합한 투자 상품과 위험 관리 상품을 함께 생성
 * 생성하는 상품 패밀리:
 *  - GrowthStock
 *  - StockRiskManagement
 */
public class AggressiveInvestmentFactory implements InvestmentProductFactory {

    // 공격형 투자에 적합한 성장주 상품을 생성
    @Override
    public InvestmentAsset createInvestmentAsset() {
        return new GrowthStock();
    }

    // 공격형 투자에 적합한 주식 위험 관리 상품을 생성
    @Override
    public RiskManagementProduct createRiskManagementProduct() {
        return new StockRiskManagement();
    }

}
