package com.pattern.behavioral.chainofresponsibility;

/**
 * 신용평가 핸들러 - 대출 심사 체인의 첫 번째 단계로 신청자의 신용점수를 검증
 * 신용점수가 기준 미달인 경우 대출 심사를 거절하고 체인을 종료
 * 신용점수가 기준을 충족하는 경우 다음 Handler에게 요청을 전달
 */
public class CreditScoreHandler extends LoanHandler {

    @Override
    public void handle(LoanRequest request) {
        System.out.println("[신용평가] 신용점수 확인");

        if (request.creditScore() < 700) {
            System.out.println("대출 거절: 신용점수 기준 미달");
            return;
        }

        System.out.println("신용평가 통과");

        // 다음 핸들러로 전달
        next(request);
    }
}
