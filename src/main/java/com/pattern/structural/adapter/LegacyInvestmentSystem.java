package com.pattern.structural.adapter;

/**
 * 레거시 투자 시스템 - 옛날 방식의 투자 메서드를 가진 클래스(과거 사용)
 * 새로운 투자 서비스와 메서드 형식이 다르기 때문에 직접 연결할 수 없지만, 어댑터를 통해 사용
 * 기존 시스템의 코드를 수정하지 않고 그대로 활용
 */
public class LegacyInvestmentSystem {

    /**
     * 기존 투자 처리 메서드 - 투자자 이름, 투자 금액, 투자 유형을 전달받아 처리
     * 새로운 시스템의 invest()와는 메서드 형태가 다름
     * @param name 투자자 이름
     * @param amount 투자 금액
     * @param type 투자 유형
     */
    public void legacyInvest(String name, int amount, String type) {
        System.out.println("[" + type + "] 투자 처리: " + name + "이(가) " + amount + "원 투자");
    }

}
