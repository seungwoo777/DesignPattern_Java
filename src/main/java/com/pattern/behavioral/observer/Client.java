package com.pattern.behavioral.observer;

public class Client {

    public static void main(String[] args) {
        EconomicEventManager eventManager = new EconomicEventManager();
        InterestRateNotifier notifier = new InterestRateNotifier(eventManager);

        // 리스너 등록
        EconomicEventListener bank = new BankListener();
        EconomicEventListener investor = new InvestorListener();

        eventManager.subscribe("기준금리변경", bank);
        eventManager.subscribe("기준금리변경", investor);

        // 기준금리 변경 → 자동 통지
        notifier.changeInterestRate("3.00%");

        System.out.println();

        // 투자자 구독 해제 후 다시 변경
        eventManager.unsubscribe("기준금리변경", investor);
        notifier.changeInterestRate("2.75%");
    }

}
