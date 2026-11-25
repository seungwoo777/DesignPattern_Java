package com.pattern.structural.facade;

import lombok.AllArgsConstructor;

/*
 * 퍼사드 패턴 (단순한 인터페이스 제공)
 * 복잡한 서브시스템들의 인터페이스를 하나의 통합된 인터페이스로 제공하는 패턴
 * 클라이언트가 여러 서브시스템을 직접 다루지 않고 퍼사드를 통해 간편하게 사용할 수 있음
 */
public class InvestmentFacade {

    private final MarketDataLoader marketDataLoader;
    private final TradingManager tradingManager;
    private final PortfolioManager portfolioManager;
    private final InvestorLoader investorLoader;

    public InvestmentFacade() {
        this.marketDataLoader = new MarketDataLoader();
        this.tradingManager = new TradingManager();
        this.portfolioManager = new PortfolioManager();
        this.investorLoader = new InvestorLoader();
    }

    public void startInvestment(String investorName, String stockName, String marketName) {
        System.out.println("=== 투자 서비스를 시작 ===");

        marketDataLoader.loadMarket(marketName);
        investorLoader.loadInvestor(investorName);
        tradingManager.executeTrade(stockName);
        portfolioManager.showPortfolio();

        System.out.println("=== " + investorName + " 투자자, " + marketName + " 시장의 " + stockName + " 투자 서비스가 완료 ===");
    }

}
