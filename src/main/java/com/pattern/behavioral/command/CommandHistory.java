package com.pattern.behavioral.command;

import java.util.Stack;

/**
 * 거래 명령 이력
 * 커맨드 히스토리 - 실행된 커맨드를 저장하고 undo를 지원
 * Stack을 이용하여 가장 최근에 실행된 거래부터 역순으로 취소할 수 있도록 구현
 */
public class CommandHistory {

    // 실행된 커맨드를 저장하는 Stack
    private final Stack<Command> history = new Stack<>();

    // 커맨드를 실행하고 히스토리에 저장
    public void execute(Command command) {
        command.execute();
        history.push(command);
    }

    // 가장 최근 커맨드를 취소
    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            System.out.println("(거래 취소 완료)");
        }
        else {
            System.out.println("취소할 거래가 없습니다.");
        }
    }

}
