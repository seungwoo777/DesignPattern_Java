package com.pattern.behavioral.chainofresponsibility;

/**
 * 책임 연쇄 패턴 (Chain of Responsibility Pattern) - 대출 심사 Handler의 추상 클래스
 *
 * 모든 대출 심사 Handler가 공통으로 가져야 하는 다음 Handler 참조와 연결 기능을 정의
 *
 * 구체적인 심사 로직은 자식 Handler가 구현 하도록 함.
 * 1. CreditScoreHandler 신용점수 검증
 * 2. IncomeHandler 소득 검증
 * 3. DebtHandler 부채 검증
 */
public abstract class LoanHandler {

    /**
     * 다음 심사 Handler를 참조하는 필드
     *
     * 현재 Handler가 심사를 통과한 경우 다음 Handler에게 요청을 전달하기 위해 사용
     *
     * 체인의 마지막 Handler라면 nextHandler가 null일 수 있음
     */
    protected LoanHandler nextHandler;

    /*
     * 다음 핸들러를 설정(체이닝 지원).
     *
     * 현재 Handler가 심사를 통과했을 때 요청을 전달할 다음 Handler를 지정
     *
     * 메서드 체이닝을 지원하기 위해 전달받은 Handler 객체를 반환 - ex) credit.setNext(income).setNext(debt);
     *
     * 다음과 같은 연결을 구성 - credit -> income -> debt
     *
     * @param handler 다음에 연결할 Handler
     * @return 연결된 다음 Handler
     */
    public LoanHandler setNext(LoanHandler handler) {
        this.nextHandler = handler; // 현재 Handler의 다음 Handler를 지정
        return handler;
    }

    /**
     * 대출 심사 처리
     *
     * 각 Handler가 자신의 심사 로직을 구현하도록 abstract로 선언
     *
     * 자식 클래스에서 반드시 구현
     *
     * @param request 대출 심사 요청 정보
     */
    public abstract void handle(LoanRequest request);

    /**
     * 다음 Handler에게 요청을 전달 공통 메서드
     *
     * 각 심사 Handler에서 심사를 통과했을 때 호출
     *
     * 다음 Handler가 존재하는 경우에만 요청을 전달
     *
     * 다음 Handler가 존재하지 않는 경우 더 이상 요청을 전달 X
     *
     * @param request 대출 심사 요청 정보
     */
    protected void next(LoanRequest request) {
        // 다음 Handler가 존재하는지 확인
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
