package com.pattern.structural.flyweight;

/*
 * 플라이웨이트 패턴 (메모리 절약을 위한 객체 공유)
 * 동일한 데이터를 가진 객체를 공유하여 메모리 사용을 최소화하는 패턴
 * 대량의 유사 객체가 필요할 때 캐시를 통해 중복 생성을 방지
 */
public interface FinancialProduct {

    void display(int accountId, int quantity);

}
