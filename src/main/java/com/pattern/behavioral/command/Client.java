package com.pattern.behavioral.command;

/**
 * 커맨드 패턴 데모 - 주식 매수, 매도 및 Undo
 * Client는 실제 거래를 수행하는 Investor에게 직접 요청하지 않고 Command 객체를 생성하여 CommandHistory에 전달
 */
public class Client {

    public static void main(String[] args) {
        // 초기 현금 1천만원 주식 보유량 0주 투자자 생성
        Investor investor = new Investor("seungwoo", 1_000_000, 0);

        // 실행된 거래 명령을 저장하고 Undo를 지원하는 이력 객체
        CommandHistory history = new CommandHistory();

        System.out.println("=== 주식 거래 시작 ===");

        history.execute(new BuyStockCommand(investor, 10, 100_000));    // 10주 주당 100,000 매수
        history.execute(new BuyStockCommand(investor,5, 120_000));      // 5주 주당 120,000 매수
        history.execute(new SellStockCommand(investor, 3, 150_000));    // 3주를 주당 150,000 매도

        System.out.println("\n현재 투자 상태: " + investor);
        System.out.println("\n=== Undo 실행 ===");

        history.undo(); // 가장 최근 거래인 매도 거래 취소
        history.undo(); // 그 이전 거래인 두 번째 매수 거래 취소

        // Undo 실행 이후 최종 투자 상태 확인
        System.out.println("\n최종 투자 상태: " + investor);
    }
}
