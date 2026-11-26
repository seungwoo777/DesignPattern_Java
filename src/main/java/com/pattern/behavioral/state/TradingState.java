package com.pattern.behavioral.state;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradingState implements ProductState {

    private final InvestmentProduct product;

    @Override
    public void invest() {
        System.out.println("거래가 활성화된 상품 추가 투자 진행");
    }

    @Override
    public void applyLoss(int loss) {
        product.setValue(product.getValue() - loss);

        System.out.println(loss + " 손실이 발생 (상품 가치: " + product.getValue() + ")");

        if (product.getValue() <= 0) {
            System.out.println("  → " + product.getName() + " 상품 가치 소진 거래 종료");
            product.setState(new ClosedState(product));
        }
    }

    @Override
    public void withdraw() {
        System.out.println("거래가 진행 중인 상품에서는 투자금 회수가 불가");
    }

}
