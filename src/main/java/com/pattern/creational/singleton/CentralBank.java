package com.pattern.creational.singleton;

/**
 * 싱글톤 패턴 (Singleton Pattern) - Bill Pugh 방식 - 클래스의 인스턴스가 오직 하나만 존재하도록 보장하는 패턴
 * 내부 정적 클래스(Inner Static Helper Class)를 활용하여 lazy loading과 thread-safe를 동시에 달성
 * 중앙은행(CentralBank)은 시스템 전체에서 단 하나의 인스턴스만 존재하도록 보장
 * 중앙은행은 기준금리와 통화 공급량을 전역적으로 관리
 */
public class CentralBank {

    private double interestRate;  // 기준금리 (단위: %)
    private long moneySupply;     // 통화 공급량 (단위: 조 원)

    // 외부에서 생성자 호출을 막음
    private CentralBank() {
        this.interestRate = 3.5;
        this.moneySupply = 1000;
    }

    /**
     * Bill Pugh 방식의 내부 정적 클래스
     * CentralBank클래스가 로드될 때가 아닌, getInstance()가 호출될 때 초기화
     * JVM의 클래스 초기화 과정에서 thread-safe가 보장
     */
    private static class InnerInstanceHolder {
        private static final CentralBank INSTANCE = new CentralBank();
    }


    // 싱글톤 인스턴스를 반환 - 호출할 때마다 새로운 객체를 생성하지 않고 동일한 중앙은행 인스턴스를 반환
    public static CentralBank getInstance() {
        return InnerInstanceHolder.INSTANCE;
    }

    /**
     * 통화 공급량을 증가
     * @param amount 증가시킬 통화량 (단위: 조 원)
     */
    public void increaseMoneySupply(long amount) {
        this.moneySupply += amount;
        System.out.println("통화 공급량 증가: " + amount + "조 원");
    }


    // 기준금리를 인상 - 기준금리를 0.25%p 인상
    public void raiseInterestRate() {
        this.interestRate += 0.25;
        System.out.println("기준금리 인상! 현재 기준금리: " + this.interestRate + "%");
    }

    /**
     * 현재 중앙은행의 경제 상태를 반환
     * @return 기준금리와 통화 공급량 정보
     */
    public String getStatus() {
        return "CentralBank [기준금리=" + interestRate + "%, 통화 공급량=" + moneySupply + "조 원]";
    }

}
