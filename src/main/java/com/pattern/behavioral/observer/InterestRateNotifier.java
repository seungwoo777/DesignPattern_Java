package com.pattern.behavioral.observer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InterestRateNotifier {

    private final EconomicEventManager eventManager;

    public void changeInterestRate(String interestRate) {
        System.out.println("기준금리가 " + interestRate + "로 변경");
        eventManager.notify("기준금리변경", interestRate);
    }

}
