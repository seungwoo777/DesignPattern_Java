package com.pattern.behavioral.templatemethod;

/*
 * 템플릿 메서드 패턴 (알고리즘의 뼈대를 정의하고 세부 구현은 하위 클래스에 위임)
 * 알고리즘의 전체 흐름은 상위 클래스에서 정의하고, 각 단계의 구체적 구현은 하위 클래스에서 담당하는 패턴
 * 코드 중복을 줄이면서 알고리즘의 구조를 유지할 수 있음.
 */
public abstract class InvestmentTemplate {

    // 템플릿 메서드: 스킬 사용 흐름을 정의 (변경 불가)
    public final void invest(String assetName) {
        analyzeMarket();
        executeInvestment(assetName);
        manageRisk();
        System.out.println();
    }

    protected abstract void analyzeMarket();

    protected abstract void executeInvestment(String assetName);

    protected abstract void manageRisk();

}
