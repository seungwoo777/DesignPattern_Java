package com.pattern.structural.adapter;

/**
 * 새로운 투자 서비스 인터페이스 - 새로운 시스템에서 투자 요청을 처리하기 위한 표준 인터페이스
 * 기존 금융 시스템의 호출 방식과 관계없이 새로운 시스템은 invest() 메서드를 통해 투자 요청을 처리
 */
public interface InvestmentService {

    void invest(String investorName, int amount);

}
