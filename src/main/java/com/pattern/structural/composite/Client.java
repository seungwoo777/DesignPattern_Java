package com.pattern.structural.composite;

/**
 * 컴포지트 패턴 데모 - 투자 포트폴리오 트리
 * 단일 투자 상품과 복합 투자 포트폴리오를 동일한 InvestmentAsset 인터페이스로 다룸
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("=== 컴포지트 패턴: 투자 포트폴리오 ===\n");

        // 최상위 포트폴리오 생성
        InvestmentPortfolio mainPortfolio = new InvestmentPortfolio("종합 투자 포트폴리오");

        // 하위 포트폴리오 생성: 주식 투자
        InvestmentPortfolio stockPortfolio = new InvestmentPortfolio("주식 포트폴리오");
        stockPortfolio.add(new SingleInvestment("삼성전자", 3_000_000));   // 주식 포트폴리오에 개별 주식 투자 추가
        stockPortfolio.add(new SingleInvestment("SK하이닉스", 5_000_000)); // 주식 포트폴리오에 개별 주식 투자 추가

        // 단일 투자 상품: 국채 투자
        SingleInvestment bondInvestment = new SingleInvestment("국채 투자", 10_000_000);

        // 최상위 포트폴리오에 하위 포트폴리오와 개별 투자 상품 추가
        mainPortfolio.add(stockPortfolio);
        mainPortfolio.add(bondInvestment);

        // 전체 투자 포트폴리오 트리 출력
        mainPortfolio.showDetail(0);
    }
}
