package com.pattern.behavioral.mediator;

/**
 * 투자자
 */
public class Investor extends MarketParticipant {

    public Investor(String name, MarketMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void receiveInformation(String information, String senderName) {
        System.out.println("  [투자자]" + name + " 수신: " + senderName + " → " + information);
    }

}
