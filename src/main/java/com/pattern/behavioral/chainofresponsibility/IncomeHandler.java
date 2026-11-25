package com.pattern.behavioral.chainofresponsibility;

/**
 * 소득심사 핸들러 - 신용평가를 통과한 요청을 전달받아 신청자의 연소득을 검증
 * 연소득이 기준 미달인 경우 대출 심사를 거절하고 체인을 종료
 * 연소득이 기준을 충족하는 경우 다음 Handler에게 요청을 전달
 */
public class IncomeHandler extends LoanHandler {

    @Override
    public void handle(LoanRequest request) {
        System.out.println("[소득심사] 연소득 확인");

        if (request.annualIncome() < 3000) {
            System.out.println("대출 거절: 소득 기준 미달");
            return;
        }

        System.out.println("소득심사 통과");

        // 다음 핸들러로 전달
        next(request);
    }
}
