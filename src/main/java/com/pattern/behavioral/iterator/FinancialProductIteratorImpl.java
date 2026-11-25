package com.pattern.behavioral.iterator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FinancialProductIteratorImpl implements FinancialProductIterator {

    private final List<FinancialProduct> products;
    private int index = 0;


    @Override
    public boolean hasNext() {
        return index < products.size();
    }

    @Override
    public FinancialProduct next() {
        return products.get(index++);
    }

}
