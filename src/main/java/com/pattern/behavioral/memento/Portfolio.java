package com.pattern.behavioral.memento;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Portfolio {

    private int investmentLevel;
    private int balance;
    private String market;

    public PortfolioState save() {
        return new PortfolioState(investmentLevel, balance, market);
    }

    public void restore(PortfolioState state) {
        this.investmentLevel = state.investmentLevel();
        this.balance = state.balance();
        this.market = state.market();
        System.out.println("투자 포트폴리오 복원 완료 → " + getStatus());
    }

    public void invest(String newMarket, int loss) {
        this.market = newMarket;
        this.balance -= loss;
        this.investmentLevel++;
        System.out.println(newMarket + " 투자 진행 (손실: " + loss + ") → " + getStatus());
    }

    public String getStatus() {
        return "투자 단계: " + investmentLevel + ", 자산: " + balance + ", 시장: " + market;
    }

}
