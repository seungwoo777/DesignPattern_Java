package com.pattern.behavioral.chainofresponsibility;

/**
 * 책임 연쇄 패턴 데모 - 대출 심사 처리 체인
 * 1. 심사 담당 Handler 객체들을 생성
 * 2. Handler들을 원하는 순서대로 연결
 * 3. 최초 Handler에게 대출 심사 요청을 전달
 * Client는 각각의 Handler가 내부적으로 어떤 심사를 수행하는지 구체적으로 알 필요 X
 */
public class Client {

    public static void main(String[] args) {
        // 핸들러 생성
        LoanHandler credit = new CreditScoreHandler();
        LoanHandler income = new IncomeHandler();
        LoanHandler debt = new DebtHandler();

        // 체인 구성: 신용평가 → 소득심사 → 부채심사
        credit.setNext(income).setNext(debt);

        // 신용점수: 750점 / 연소득: 5,000만 원 / 기존부채: 2,000만 원
        LoanRequest request = new LoanRequest(750, 5000, 2000);

        System.out.println("=== 대출 심사 시작 ===");

        credit.handle(request);
    }
}
