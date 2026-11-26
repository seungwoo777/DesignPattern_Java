package com.pattern.behavioral.observer;

/*
 * 옵저버 패턴 (상태 변화 통보 및 자동 업데이트)
 * 객체의 상태 변화를 관찰하는 옵저버 목록을 관리하고, 변화가 발생하면 자동으로 통지하는 패턴
 * 발행-구독(Pub-Sub) 구조로 느슨한 결합을 유지하면서 이벤트 기반 시스템을 구현할 수 있음
 */
public interface EconomicEventListener {

    void update(String eventType, String data);

}
