package com.pattern.behavioral.chainofresponsibility;

import lombok.AllArgsConstructor;

/**
 * @param creditScore  신청자의 신용점수
 * @param annualIncome 신청자의 연소득
 * @param existingDebt 신청자의 기존 부채
 */ /*
 * 대출 심사 요청 정보를 담는 클래스
 *
 * 각 Handler는 이 객체를 전달받아 자신이 담당하는 심사에 필요한 정보를 확인
 * 1. 신용평가 handler -> creditScore
 * 2. 소득심사 handler -> annualIncome
 * 3. 부채심사 handler -> existingDebt
 *
 * 모든 Handler가 동일한 요청 객체를 전달받기 때문에 심사 단계마다 별도의 데이터 전달 객체를 만들 필요 X
 */
public record LoanRequest(int creditScore, int annualIncome, int existingDebt) {

}
