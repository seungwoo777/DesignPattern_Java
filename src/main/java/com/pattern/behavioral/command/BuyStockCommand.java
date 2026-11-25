package com.pattern.behavioral.command;

import lombok.AllArgsConstructor;

/**
 * 주식 매수 커맨드
 * Investor에게 주식 매수를 요청하는 행동을 객체로 캡슐화
 * execute() -> 주식 매수
 * undo()    -> 매수했던 주식을 다시 매도하여 매수 거래 취소
 */
@AllArgsConstructor
public class BuyStockCommand implements Command {

    private Investor investor; // 실제 거래를 수행하는 투자자
    private int quantity;      // 매수할 주식 수량
    private int price;         // 주식 1주당 매수 가격


    @Override
    public void execute() {
        investor.buyStock(quantity, price); // Investor에게 주식 매수를 요청
    }

    @Override
    public void undo() {
        // 매수 명령 취소 - 매수했던 주식을 동일한 수량과 가격으로 매도하여 매수 거래 이전의 상태로 되돌림
        investor.sellStock(quantity, price);
    }

}
