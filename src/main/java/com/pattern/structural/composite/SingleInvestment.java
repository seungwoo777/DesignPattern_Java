package com.pattern.structural.composite;

import lombok.AllArgsConstructor;

/**
 * 단일 투자 상품 (Leaf 노드)
 * 더 이상 하위 투자 상품을 포함하지 않는 개별 투자 자산 - 컴포지트 패턴에서 Leaf 역할을 수행
 */
@AllArgsConstructor
public class SingleInvestment implements InvestmentAsset {

    private final String name; // 투자 상품명
    private final long amount; // 투자 금액

    // 투자 상품 상세 정보를 출력
    @Override
    public void showDetail(int depth) {
        // 트리 구조를 표현하기 위한 들여쓰기
        String indent = "  ".repeat(depth);
        // 투자 상품명과 투자 금액 출력
        System.out.println(indent + name + " (투자 금액: " + String.format("%,d", amount) + "원)");
    }

}
