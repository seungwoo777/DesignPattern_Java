package com.pattern.structural.decorator;

public class Client {

    public static void main(String[] args) {
        // 기본 투자 상품 생성
        Investment investment = new BasicInvestment("기본 투자 상품");
        System.out.println(investment.getDescription() + " | 수익 점수: " + investment.getReturn());

        // 배당 수익 추가
        Investment decorated1 = new DividendDecorator(investment);
        System.out.println(decorated1.getDescription() + " | 수익 점수: " + decorated1.getReturn());

        // 배당 수익 + 세제 혜택 추가
        Investment decorated2 = new TaxBenefitDecorator(decorated1);
        System.out.println(decorated2.getDescription() + " | 수익 점수: " + decorated2.getReturn());

        // 배당 수익 + 세제 혜택 + 복리 효과 추가
        Investment decorated3 = new CompoundInterestDecorator(decorated2);
        System.out.println(decorated3.getDescription() + " | 수익 점수: " + decorated3.getReturn());
    }
}
