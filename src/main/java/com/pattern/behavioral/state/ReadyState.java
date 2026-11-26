package com.pattern.behavioral.state;

public class ReadyState implements ProductState {

    private final InvestmentProduct product;

    public ReadyState(InvestmentProduct product) {
        this.product = product;
    }

    @Override
    public void invest() {
        System.out.println("투자 주문 접수 거래 활성화");
        product.setState(new TradingState(product));
    }

    @Override
    public void applyLoss(int loss) {
        product.setValue(product.getValue() - loss);

        System.out.println("시장 변동으로 " + loss + " 손실 발생 (상품 가치: " + product.getValue() + ") → 거래 활성화");

        product.setState(new TradingState(product));

        if (product.getValue() <= 0) {
            System.out.println("  → " + product.getName() + " 상품 가치 소진 거래 종료");
            product.setState(new ClosedState(product));
        }
    }

    @Override
    public void withdraw() {
        System.out.println("아직 투자하지 않은 상품");
    }

}
