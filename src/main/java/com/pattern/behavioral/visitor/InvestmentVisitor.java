package com.pattern.behavioral.visitor;

/**
 * 투자 상품 방문자 인터페이스
 */
public interface InvestmentVisitor {

    void visit(StockInvestment stock);

    void visit(BondInvestment bond);

    void visit(DepositInvestment deposit);

}
