package com.pattern.behavioral.iterator;

/*
 * 이터레이터 패턴 (컬렉션 순회 방법 캡슐화)
 * 컬렉션의 내부 구조를 노출하지 않고 원소를 순회할 수 있게 해주는 패턴
 * 다양한 순회 전략을 독립적으로 구현할 수 있음.
 */
public interface FinancialProductIterator  {

    boolean hasNext();

    FinancialProduct next();

}
