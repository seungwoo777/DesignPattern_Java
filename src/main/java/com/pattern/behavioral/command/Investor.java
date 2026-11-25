package com.pattern.behavioral.command;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 플레이어 클래스 - 주식 매수, 매도 및 Undo
 * Client는 실제 거래를 수행하는 Investor에게 직접 요청하지 않고 Command 객체를 생성하여 CommandHistory에 전달
 */
@ToString
@AllArgsConstructor
public class Investor {

    private String name;
    private int cash;
    private int stockQuantity;

    // 주식 매수 - 매수에 필요한 금액을 계산하고 현금이 충분하면 현금을 차감하고 주식 수량을 증가
    public void buyStock(int quantity, int price) {
        // 총 매수 금액 계산
        int totalPrice = quantity * price;

        cash -= totalPrice;        // 매수 금액만큼 현금 차감
        stockQuantity += quantity; // 매수한 수량만큼 주식 보유량 증가

        System.out.println(name + " 매수: " + quantity + "주, " + "주당 " + price + "원" + " | 현금: " + cash + "원" + " | 보유 주식: " + stockQuantity + "주");
    }

    // 주식 매도 - 매도 금액을 계산하고 현금 증가 및 주식 보유량 감소를 수행
    public void sellStock(int quantity, int price) {
        // 총 매도 금액 계산
        int totalPrice = quantity * price;

        cash += totalPrice;        // 매도 금액만큼 현금 증가
        stockQuantity -= quantity; // 매도한 수량만큼 주식 보유량 감소

        System.out.println(name + " 매도: " + quantity + "주, " +  "주당 " + price + "원" + " | 현금: " + cash + "원" + " | 보유 주식: " + stockQuantity + "주");
    }

}
