package com.pattern.behavioral.command;

/**
 * 커맨드 패턴 (Command Pattern)
 * 요청(행동)을 객체로 캡슐화하여 실행, 취소(undo) 등의 기능을 지원
 * 주식 매수와 매도 거래를 객체로 캡슐화하여 실행(execute)과 취소(undo)를 구현
 */
public interface Command {

    // 명령 실행
    void execute();

    // 명령 취소
    void undo();

}
