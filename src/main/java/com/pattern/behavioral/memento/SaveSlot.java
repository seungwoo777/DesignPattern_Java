package com.pattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class SaveSlot {

    private final List<PortfolioState> saves = new ArrayList<>();

    public void saveGame(PortfolioState state) {
        saves.add(state);
        System.out.println("슬롯 " + (saves.size() - 1) + "에 저장 완료! (" + state + ")");
    }

    public PortfolioState loadGame(int slot) {
        if (slot < 0 || slot >= saves.size()) {
            System.out.println("해당 슬롯에 저장된 데이터가 없습니다.");
            return null;
        }
        return saves.get(slot);
    }

    public void showSaves() {
        System.out.println("=== 투자 기록 목록 ===");
        for (int i = 0; i < saves.size(); i++) {
            System.out.println("슬롯 " + i + ": " + saves.get(i));
        }
    }

}
