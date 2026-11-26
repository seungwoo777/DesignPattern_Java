package com.pattern.behavioral.state;

import lombok.Getter;
import lombok.Setter;

public class InvestmentProduct {

    @Getter
    private final String name;
    @Getter @Setter
    private int value;
    @Setter
    private ProductState state;

    public InvestmentProduct(String name, int value) {
        this.name = name;
        this.value = value;
        this.state = new ReadyState(this);
    }

    public void invest() {
        System.out.print("[" + name + "] ");
        state.invest();
    }

    public void applyLoss(int loss) {
        System.out.print("[" + name + "] ");
        state.applyLoss(loss);
    }

    public void withdraw() {
        System.out.print("[" + name + "] ");
        state.withdraw();
    }


}
