package com.pattern.behavioral.chainofresponsibility;

/**
 * 부채심사 핸들러 - 신용평가와 소득심사를 통과한 요청을 전달받아 신청자의 기존 부채를 검증
 * 기존 부채가 기준을 초과하는 경우 대출 심사를 거절
 * 기존 부채가 기준을 충족하는 경우 최종 대출 승인 결과를 출력
 */
public class DebtHandler extends LoanHandler {

    @Override
    public void handle(LoanRequest request) {
        System.out.println("[부채심사] 기존 부채 확인");

        if (request.existingDebt() >= 10000) {
            System.out.println("대출 거절: 기존 부채 기준 초과");
            return;
        }
        System.out.println("부채심사 통과");

        // 최종 핸들러
        System.out.println("최종 결과: 대출 승인");
    }
}
