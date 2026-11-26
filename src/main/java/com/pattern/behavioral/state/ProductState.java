package com.pattern.behavioral.state;

/*
 * 상태 패턴 (상태에 따라 행동이 달라지는 객체)
 * 객체의 내부 상태에 따라 행동을 변경하는 패턴
 * 상태를 클래스로 분리하여 조건문 없이 상태별 행동을 깔끔하게 구현할 수 있음.
 */
public interface ProductState  {

    void invest();

    void applyLoss(int loss);

    void withdraw();

}
