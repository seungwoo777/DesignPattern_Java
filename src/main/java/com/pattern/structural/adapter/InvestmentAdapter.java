package com.pattern.structural.adapter;

import lombok.AllArgsConstructor;

/**
 * 어댑터 패턴 (Adapter Pattern)
 * 호환되지 않는 인터페이스를 가진 클래스를 함께 동작할 수 있도록 변환하는 패턴
 * 기존 레거시 시스템을 새로운 인터페이스에 맞춰야 할 때 유용
 * 기존 코드를 수정하지 않고도 새로운 시스템과 통합할 수 있음.
 */
@AllArgsConstructor
public class InvestmentAdapter implements InvestmentService {

    // 기존 금융 시스템 객체를 보관
    private final LegacyInvestmentSystem legacyInvestmentSystem;

    @Override
    public void invest(String investorName, int amount) {
        // 새 인터페이스의 호출을 레거시 시스템의 메서드로 위임 - 새로운 투자 요청을 기존 금융 시스템의 호출 방식으로 변환
        legacyInvestmentSystem.legacyInvest(investorName, amount, "일반 투자");
    }

}
