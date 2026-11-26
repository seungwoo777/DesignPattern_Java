package com.pattern.behavioral.observer;

public class BankListener implements EconomicEventListener {

    @Override
    public void update(String eventType, String data) {
        System.out.println("[은행] 기준금리 " + data + " 적용 대출 및 예금 금리 조정 검토");
    }

}
