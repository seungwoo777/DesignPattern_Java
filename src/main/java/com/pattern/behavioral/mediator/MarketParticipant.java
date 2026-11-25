package com.pattern.behavioral.mediator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 시장 참여자
 */
@AllArgsConstructor
public abstract class MarketParticipant {

    @Getter
    protected final String name;
    protected final MarketMediator mediator;

    public void publish(String information) {
        System.out.println(name + " → " + information);
        mediator.publishInformation(information, this);
    }

    public abstract void receiveInformation(String information, String senderName);

}
