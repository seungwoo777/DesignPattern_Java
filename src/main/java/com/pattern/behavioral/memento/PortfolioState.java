package com.pattern.behavioral.memento;

import lombok.AllArgsConstructor;

/*
 * 메멘토 패턴 (객체 상태 저장 및 복원)
 * 객체의 내부 상태를 외부에 저장해두었다가 필요할 때 복원하는 패턴
 * 캡슐화를 유지하면서 이전 상태로 되돌리기(Undo) 기능을 구현할 수 있음
 */
public record PortfolioState(int investmentLevel, int balance, String market) {

}
