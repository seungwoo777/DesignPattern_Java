package com.pattern.creational.singleton;

/**
 * 싱글톤 패턴 클라이언트
 * CentralBank 인스턴스가 하나만 존재하는지 확인
 */
public class Client {

    public static void main(String[] args) {
        // 두 개의 참조 변수로 인스턴스를 가져온다
        CentralBank bank1 = CentralBank.getInstance();
        CentralBank bank2 = CentralBank.getInstance();

        // 두 참조 변수가 동일한 객체를 참조하는지 확인한다.
        System.out.println("bank1 == bank2 -> " + (bank1 == bank2));
        System.out.println("bank1 hashCode -> " + bank1.hashCode());
        System.out.println("bank2 hashCode -> " + bank2.hashCode());

        System.out.println();

        // bank1을 통해 통화 공급량을 증가시키고 기준금리를 인상
        bank1.increaseMoneySupply(100);
        bank1.raiseInterestRate();
        System.out.println("bank1 상태: " + bank1.getStatus());

        System.out.println();

        // bank2를 통해서도 동일한 중앙은행 상태를 변경 - 같은 상태가 반영되는지 확인
        bank2.increaseMoneySupply(200);
        bank2.raiseInterestRate();
        System.out.println("bank2 상태: " + bank2.getStatus());

        System.out.println();

        // 동일한 인스턴스이므로 결과가 같음
        System.out.println("bank1 상태: " + bank1.getStatus());
    }

}
