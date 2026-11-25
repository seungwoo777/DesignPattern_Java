package com.pattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 금융시장
 */
public class FinancialMarket implements MarketMediator {

    private final List<MarketParticipant> participants = new ArrayList<>();

    @Override
    public void addParticipant(MarketParticipant participant) {
        participants.add(participant);
    }

    @Override
    public void publishInformation(String information, MarketParticipant sender) {
        for (MarketParticipant participant : participants) {
            if (participant != sender) {
                participant.receiveInformation(information, sender.getName());
            }
        }
    }

}
