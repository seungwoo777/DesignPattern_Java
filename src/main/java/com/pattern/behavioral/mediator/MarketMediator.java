package com.pattern.behavioral.mediator;

/*
 * 시장 중재자
 * 중재자 패턴 (객체 간 통신을 중재자를 통해 캡슐화)
 * 객체들이 서로 직접 참조하지 않고 중재자를 통해 소통하는 패턴
 * 객체 간 결합도를 줄이고 상호작용을 한 곳에서 관리할 수 있음
 */
public interface MarketMediator {

    void publishInformation(String information, MarketParticipant sender);

    void addParticipant(MarketParticipant participant);

}
