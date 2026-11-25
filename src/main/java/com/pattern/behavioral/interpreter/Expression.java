package com.pattern.behavioral.interpreter;

/*
 * 인터프리터 패턴 (문법 규칙을 클래스로 표현)
 * 간단한 언어의 문법을 정의하고 그 문장을 해석하는 패턴
 * 각 문법 규칙을 클래스로 표현하여 언어를 쉽게 확장할 수 있음
 */
public interface Expression {

    int interpret(FinancialContext context);

}
