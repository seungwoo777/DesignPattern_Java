package com.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class FinancialProductRepository {

    private final List<FinancialProduct> products = new ArrayList<>();

    public void addProduct(FinancialProduct product) {
        products.add(product);
    }

    public FinancialProductIterator createIterator() {
        return new FinancialProductIteratorImpl(products);
    }

    public FinancialProductIterator createTypeIterator(String type) {
        return new TypeFilterIterator(products, type);
    }

}
