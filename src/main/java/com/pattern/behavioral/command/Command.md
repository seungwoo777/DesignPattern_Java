# 커맨드 패턴 (Command Pattern)

## 패턴 개요
요청(행위)을 객체로 캡슐화하여 요청의 실행, 취소(Undo), 큐잉, 로깅 등을 가능하게 하는 패턴

## 특징
- 행위를 객체로 캡슐화 (실행할 작업을 객체에 담아서 전달)
- 실행 취소(Undo) / 재실행(Redo) 구현에 적합
- 요청을 큐에 저장하거나 로그로 기록 가능
- Invoker(호출자)와 Receiver(수행자)를 분리

## 장점
- 단일 책임 원칙(SRP) → 호출/수행 분리
- 개방-폐쇄 원칙(OCP) → 새 커맨드 추가 용이
- Undo/Redo, 큐잉, 지연 실행, 매크로 커맨드 등 다양한 활용

## 단점
- 커맨드 클래스가 많아질 수 있음
- 간단한 작업에는 과도한 구조

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Batch Tasklet/Step** | 각 Tasklet/Step이 하나의 Command로서 Job에 의해 순차 실행 |
| **@Async 작업** | `Runnable`/`Callable` 커맨드 객체를 `TaskExecutor`에 제출하여 비동기 실행 |
| **ApplicationEvent** | 이벤트를 커맨드 객체로 발행하고, `@EventListener`가 수행 |
| **Spring Scheduler** | `@Scheduled` 작업이 내부적으로 `Runnable` 커맨드 객체로 관리 |
| **Spring Integration Message** | 메시지가 커맨드 객체로서 채널을 통해 핸들러에 전달되어 실행 |
| **CQRS (Command Query Responsibility Segregation)** | 쓰기 작업을 Command 객체로 분리. Spring에서 `CommandHandler` 패턴으로 구현 |
| **Spring Shell** | CLI 명령어를 커맨드 객체로 매핑하여 실행 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **Runnable / Callable** | 스레드 작업을 커맨드 객체로 캡슐화. `executor.submit(callable)` |
| **java.awt.event.ActionListener** | GUI 버튼 클릭 등 이벤트를 커맨드 패턴으로 처리. `button.addActionListener(e -> ...)` |
| **java.util.Timer / TimerTask** | 예약 작업을 `TimerTask`(커맨드 객체)로 캡슐화하여 스케줄링 |
| **CompletableFuture** | 비동기 작업을 커맨드 체인으로 조합. `thenApply()`, `thenAccept()`, `thenCompose()` |
| **Thread.UncaughtExceptionHandler** | 예외 처리를 핸들러 커맨드 객체에 위임 |
| **java.lang.ProcessBuilder** | 외부 프로세스 실행을 커맨드 객체로 캡슐화 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **HTTP 요청 자체** | HTTP Request가 메서드(GET/POST/PUT/DELETE) + URL + Body를 담은 커맨드 객체 |
| **REST API 설계** | POST /orders (CreateOrderCommand), DELETE /orders/1 (DeleteOrderCommand) → 각 요청이 커맨드 |
| **gRPC 서비스 메서드** | 각 RPC 메서드가 Request 객체(커맨드)를 받아 처리 |
| **WebSocket 메시지** | 클라이언트가 보내는 각 메시지가 커맨드 객체 (채팅 전송, 게임 조작 등) |
| **GraphQL Mutation** | `mutation { createUser(input: {...}) }` → Mutation이 커맨드 역할 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **SQL 문 자체** | INSERT/UPDATE/DELETE SQL이 DB에 대한 커맨드 객체. PreparedStatement가 이를 캡슐화 |
| **트랜잭션 로그 (WAL)** | Write-Ahead Log가 커맨드(변경 작업)를 순서대로 기록 → 장애 시 재실행(Redo) 또는 취소(Undo) |
| **이벤트 소싱 (Event Sourcing)** | 도메인 이벤트(커맨드)를 순서대로 저장하고, 재생하여 상태를 복원 |
| **Migration 도구 (Flyway/Liquibase)** | 각 마이그레이션 스크립트가 커맨드 객체. 순차 실행 + 롤백(Undo) 지원 |
| **Redis Command** | `SET`, `GET`, `LPUSH` 등 각 Redis 명령이 커맨드 객체. AOF 로그에 기록하여 복원 |
| **MongoDB Oplog** | 변경 작업을 Oplog에 커맨드로 기록하여 Replica Set 동기화 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **쉘 커맨드** | `ls`, `grep`, `awk` 등 각 셸 명령어가 커맨드 객체. 파이프로 체이닝, 히스토리로 기록 |
| **Job Queue (Celery, Sidekiq)** | 작업을 커맨드 객체로 큐에 넣고, 워커가 꺼내서 실행 |
| **Makefile** | 각 타겟이 커맨드 객체. 의존성 순서에 따라 실행 |
| **Git** | `git commit`, `git revert`, `git rebase` 등 각 명령이 커맨드 패턴. `reflog`로 히스토리 관리 |
| **Ctrl+Z (Undo)** | OS/에디터의 Undo 기능이 커맨드 스택으로 구현 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Command Stack (Undo/Redo)** | 실행된 커맨드를 스택에 Push, Undo 시 Pop하여 역연산 실행 |
| **Task Queue** | 커맨드 객체를 큐에 넣고 순서대로 또는 병렬로 실행 |
| **Macro Command** | 여러 커맨드를 하나의 복합 커맨드로 묶어 일괄 실행 (Composite + Command) |
| **Callback 패턴** | 함수/람다를 커맨드 객체로 전달하여 나중에 실행 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Producer Record** | 각 메시지가 토픽에 대한 커맨드. 키/값/헤더를 담아 전송 |
| **RabbitMQ Message** | 메시지가 큐에 저장되어 컨슈머가 꺼내 실행하는 커맨드 패턴 |
| **Saga 보상 트랜잭션** | 각 서비스의 실행 커맨드 + 보상(Undo) 커맨드를 쌍으로 관리 |
| **Dead Letter Queue** | 실패한 커맨드(메시지)를 DLQ에 저장하여 나중에 재처리 |
| **AWS SQS / SNS** | 작업을 메시지(커맨드)로 큐에 넣고 비동기로 처리 |
