package com.pattern.behavioral.memento;

public class Client {

    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio(1, 10000, "국내 주식 시장");
        SaveSlot saveSlot = new SaveSlot();

        System.out.println("초기 상태: " + portfolio.getStatus());

        // 시장 투자 진행 후 세이브
        portfolio.invest("미국 주식 시장", 2000);
        saveSlot.saveGame(portfolio.save());

        // 추가 투자 진행 후 세이브
        portfolio.invest("유럽 주식 시장", 3000);
        saveSlot.saveGame(portfolio.save());

        // 추가 투자 → 위험한 시장 상황
        portfolio.invest("신흥국 시장", 8000);
        System.out.println("\n현재 상태: " + portfolio.getStatus());

        // 세이브 목록 확인 후 로드
        System.out.println();
        saveSlot.showSaves();

        System.out.println("\n슬롯 0 로드:");
        portfolio.restore(saveSlot.loadGame(0));
    }

}
