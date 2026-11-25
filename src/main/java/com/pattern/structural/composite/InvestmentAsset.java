package com.pattern.structural.composite;

/**
 * 컴포지트 패턴의 컴포넌트 인터페이스
 * 개별 투자 상품과 투자 포트폴리오 그룹이 동일한 인터페이스를 구현하도록 정의
 */
public interface InvestmentAsset {

    /**
     * 투자 자산의 상세 정보를 출력한다.
     * @param depth 들여쓰기 레벨
     */
    void showDetail(int depth);

}
