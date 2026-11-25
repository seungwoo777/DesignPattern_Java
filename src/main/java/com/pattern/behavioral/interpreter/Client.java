package com.pattern.behavioral.interpreter;

public class Client {

    public static void main(String[] args) {
        // 금융 컨텍스트 설정
        FinancialContext context = new FinancialContext();
        context.set("주식매수금액", 30_000);
        context.set("주식매도금액", 25_000);
        context.set("거래수수료", 1_500);

        // 수식: 주식매도금액 - 주식매수금액 - 거래수수료
        Expression expression = new SubtractExpression(
                new AddExpression(new VariableExpression("주식매도금액"), new VariableExpression("주식매수금액")),
                new VariableExpression("거래수수료")
        );

        int result = expression.interpret(context);
        System.out.println("주식 매도금액(25,000) - 매수금액(30,000) - 거래수수료(1,500) = " + result);

        // 수식: 주식매수금액 + 추가 투자금(10,000)
        Expression investmentExpression = new AddExpression(new VariableExpression("주식매수금액"), new NumberExpression(10_000));
        System.out.println("주식 매수금액(30,000) + 추가 투자금(10,000) = " + investmentExpression.interpret(context));
    }

}
