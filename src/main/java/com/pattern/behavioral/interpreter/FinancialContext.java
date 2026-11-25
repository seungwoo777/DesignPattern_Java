package com.pattern.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

public class FinancialContext {

    private final Map<String, Integer> variables = new HashMap<>();

    public int get(String name) {
        return variables.getOrDefault(name, 0);
    }

    public void set(String name, int value) {
        variables.put(name, value);
    }

}
