package com.pattern.behavioral.iterator;

import java.util.List;
import java.util.stream.Collectors;

public class TypeFilterIterator implements FinancialProductIterator {

    private final List<FinancialProduct> filteredProducts;
    private int index = 0;

    public TypeFilterIterator(List<FinancialProduct> products, String type) {
        this.filteredProducts = products.stream()
                .filter(product -> product.type().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return index < filteredProducts.size();
    }

    @Override
    public FinancialProduct next() {
        return filteredProducts.get(index++);
    }

}
