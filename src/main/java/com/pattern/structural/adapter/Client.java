package com.pattern.structural.adapter;

/**
 * 어댑터 패턴 데모 - 기존 금융 시스템 연동
 * 기존 금융 시스템을 어댑터로 감싸서 새로운 투자 서비스 인터페이스로 사용하는 예제
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("=== 어댑터 패턴: 기존 금융 시스템 연동 ===\n");

        // 레거시 시스템을 어댑터로 감싸서 새 인터페이스로 사용
        // 기존 금융 시스템 객체 생성
        LegacyInvestmentSystem legacySystem = new LegacyInvestmentSystem();
        // 기존 금융 시스템을 새로운 투자 서비스 인터페이스에 맞게 변환
        InvestmentService investmentService = new InvestmentAdapter(legacySystem);

        // 새로운 투자 서비스 인터페이스를 통해 투자 요청
        investmentService.invest("A", 500000);
        investmentService.invest("B", 300000);
        investmentService.invest("C", 400000);
    }

}
