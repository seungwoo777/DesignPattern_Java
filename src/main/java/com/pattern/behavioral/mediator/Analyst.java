package com.pattern.behavioral.mediator;

/**
 * 애널리스트
 */
public class Analyst extends MarketParticipant {

    public Analyst(String name, MarketMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void receiveInformation(String information, String senderName) {
        System.out.println("  [애널리스트]" + name + " 수신: " + senderName + " → " + information);
    }

}
