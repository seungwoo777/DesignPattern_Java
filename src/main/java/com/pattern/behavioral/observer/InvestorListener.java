package com.pattern.behavioral.observer;

public class InvestorListener implements EconomicEventListener {

    @Override
    public void update(String eventType, String data) {
        System.out.println("[투자자] 기준금리 " + data + " 변경 투자 포트폴리오 조정 준비");
    }

}
