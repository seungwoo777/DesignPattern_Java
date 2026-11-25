package com.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FinancialProductFactory {

    private final Map<String, FinancialProduct> cache = new HashMap<>();

    public FinancialProduct getProduct(String name, String riskType) {
        String key = name + "_" + riskType;

        if (!cache.containsKey(key)) {
            cache.put(key, new FinancialProductImpl(name, riskType));
            System.out.println("[FinancialProductFactory] 새로운 금융 상품 생성: "
                    + name + "(" + riskType + ")");
        }

        return cache.get(key);
    }

    public int getCacheSize() {
        return cache.size();
    }

}
