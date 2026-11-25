package com.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 컴포지트 패턴 (Composite Pattern)
 *
 * 객체들을 트리 구조로 구성하여 부분-전체 계층을 표현하는 패턴
 * 개별 객체와 복합 객체를 동일한 인터페이스로 다룰 수 있다.
 *
 * 여러 투자 상품 또는 다른 투자 포트폴리오를 포함할 수 있는 복합 투자 자산
 * 개별 투자 상품과 복합 투자 포트폴리오를 InvestmentAsset 인터페이스로 동일하게 처리
 */
public class InvestmentPortfolio implements InvestmentAsset {

    private final String name; // 포트폴리오 이름
    private final List<InvestmentAsset> children = new ArrayList<>();  // 포트폴리오에 포함된 투자 자산 목록

    public InvestmentPortfolio(String name) {
        this.name = name;
    }

    /**
     * 포트폴리오에 투자 자산 추가 - 단일 투자 상품과 다른 포트폴리오 모두 추가할 수 있음.
     * @param asset 추가할 투자 자산
     */
    public void add(InvestmentAsset asset) {
        children.add(asset);
    }

    /**
     * 포트폴리오에서 투자 자산 제거
     * @param asset 제거할 투자 자산
     */
    public void remove(InvestmentAsset asset) {
        children.remove(asset);
    }

    /**
     * 포트폴리오 상세 정보를 출력한다.
     * @param depth 들여쓰기 레벨
     */
    @Override
    public void showDetail(int depth) {
        // 트리 구조를 표현하기 위한 들여쓰기
        String indent = "  ".repeat(depth);

        // 포트폴리오 이름 출력
        System.out.println(indent + "[투자 포트폴리오] " + name);

        // 하위 투자 자산들의 상세 정보 출력
        for (InvestmentAsset child : children) {
            child.showDetail(depth + 1);
        }
    }

}
