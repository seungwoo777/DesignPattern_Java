package com.pattern.behavioral.iterator;

public class Client {

    public static void main(String[] args) {
        FinancialProductRepository repository = new FinancialProductRepository();
        repository.addProduct(new FinancialProduct("정기예금", "예금"));
        repository.addProduct(new FinancialProduct("국채 10년물", "채권"));
        repository.addProduct(new FinancialProduct("KOSPI 200 ETF", "펀드"));
        repository.addProduct(new FinancialProduct("적금 상품", "예금"));
        repository.addProduct(new FinancialProduct("회사채", "채권"));

        // 전체 금융 상품 순회
        System.out.println("=== 전체 금융 상품 ===");
        FinancialProductIterator allIterator = repository.createIterator();
        while (allIterator.hasNext()) {
            System.out.println(allIterator.next());
        }

        // 예금 상품만 필터링 순회
        System.out.println("\n=== 예금 상품만 필터링 ===");
        FinancialProductIterator depositIterator = repository.createTypeIterator("예금");
        while (depositIterator.hasNext()) {
            System.out.println(depositIterator.next());
        }
    }
}
