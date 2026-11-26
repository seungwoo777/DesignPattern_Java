package com.pattern.behavioral.state;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClosedState implements ProductState {

    private final InvestmentProduct product;

    @Override
    public void invest() {
        System.out.println("거래 종료된 상품 투자 X");
    }

    @Override
    public void applyLoss(int loss) {
        System.out.println("거래 종료된 상품 추가 손실을 반영 X");
    }

    @Override
    public void withdraw() {
        System.out.println("거래 종료된 상품 추가 회수 처리 불가");
    }

}
