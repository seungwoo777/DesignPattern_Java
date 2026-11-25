package com.pattern.structural.proxy;

/*
 * 프록시 패턴 (접근 제어 및 추가 기능 제공)
 * 대상 객체에 대한 접근을 제어하는 대리 객체를 제공하는 패턴
 * 접근 제어, 지연 초기화, 로깅 등 원본 객체를 수정하지 않고 부가 기능을 추가할 수 있음
 */
public interface InvestmentProduct {

    void subscribe(String investorName, int investorGrade);

}
