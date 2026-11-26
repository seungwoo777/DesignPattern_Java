package com.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EconomicEventManager {

    private final Map<String, List<EconomicEventListener>> listeners = new HashMap<>();

    public void subscribe(String eventType, EconomicEventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(String eventType, EconomicEventListener listener) {
        List<EconomicEventListener> list = listeners.get(eventType);

        if (list != null) {
            list.remove(listener);
        }
    }

    public void notify(String eventType, String data) {
        List<EconomicEventListener> list = listeners.get(eventType);

        if (list != null) {
            for (EconomicEventListener listener : list) {
                listener.update(eventType, data);
            }
        }
    }

}
