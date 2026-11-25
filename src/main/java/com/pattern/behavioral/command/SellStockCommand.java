package com.pattern.behavioral.command;

import lombok.AllArgsConstructor;

/**
 * 주식 매도 커맨드
 * Investor에게 주식 매도를 요청하는 행동을 객체로 캡슐화
 * execute() -> 주식 매도
 * undo()    -> 매도했던 주식을 다시 매수하여 매도 거래 취소
 */
@AllArgsConstructor
public class SellStockCommand implements Command {

    private Investor investor; // 실제 거래를 수행하는 투자자
    private int quantity;      // 매수할 주식 수량
    private int price;         // 주식 1주당 매수 가격

    @Override
    public void execute() {
        investor.sellStock(quantity, price); // Investor에게 주식 매도를 요청
    }

    @Override
    public void undo() {
        // 매도 명령 취소 - 매도했던 주식을 동일한 수량과 가격으로 다시 매수하여 매도 거래 이전의 상태로 되돌림
        investor.buyStock(quantity, price);
    }
}
