# 상태 패턴 (State Pattern)

## 패턴 개요
객체의 내부 상태에 따라 행동을 변경하는 패턴. 상태를 별도의 클래스로 분리하여 조건문(if-else) 없이 상태별 행동을 깔끔하게 구현한다.

## 특징
- 상태를 클래스로 캡슐화하여 행동과 함께 관리
- 상태 전이(Transition) 로직이 각 상태 클래스에 분산
- Context 객체가 현재 상태 객체에 행동을 위임
- 유한 상태 머신(FSM)의 객체지향적 구현

## 장점
- 단일 책임 원칙(SRP) → 상태별 로직이 분리
- 개방-폐쇄 원칙(OCP) → 새 상태 추가 용이
- 복잡한 조건문 제거 → 가독성 향상

## 단점
- 상태가 적으면 과도한 구조
- 상태 전이 로직이 분산되어 전체 흐름 파악이 어려울 수 있음

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring State Machine** | Spring에서 제공하는 공식 상태 머신 프레임워크 (`spring-statemachine`). State, Transition, Guard, Action 정의 |
| **Spring Batch Job/Step Status** | STARTING → STARTED → COMPLETED / FAILED / STOPPED / ABANDONED 상태 전이에 따라 다른 처리 |
| **Spring Security Authentication State** | 미인증 → 인증중 → 인증완료 → 세션만료 상태에 따라 SecurityFilter 체인의 행동이 달라짐 |
| **Spring Integration Message Flow** | 메시지 처리 상태(pending/processing/completed/error)에 따라 다른 채널로 라우팅 |
| **Spring WebFlux Connection State** | WebSocket/SSE 연결 상태(connecting/open/closing/closed)에 따라 다른 행동 |
| **Spring Cloud Circuit Breaker** | CLOSED → OPEN → HALF_OPEN 상태에 따라 요청 허용/차단/시험적 허용이 달라짐 |
| **Spring Retry RetryContext** | 재시도 상태(시도 횟수, 마지막 예외)에 따라 재시도/백오프/포기 결정 |
| **Spring Boot Actuator Health** | UP / DOWN / OUT_OF_SERVICE / UNKNOWN 상태에 따라 Health Check 응답이 달라짐 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.lang.Thread.State** | NEW → RUNNABLE → BLOCKED → WAITING → TIMED_WAITING → TERMINATED 상태 전이 |
| **java.net.Socket** | CREATED → BOUND → CONNECTED → CLOSE_WAIT → CLOSED 상태에 따라 I/O 행동이 달라짐 |
| **java.util.concurrent.FutureTask** | NEW → COMPLETING → NORMAL / EXCEPTIONAL / CANCELLED 상태에 따라 get() 행동이 달라짐 |
| **java.util.stream.Stream** | 스트림 파이프라인 상태(빌드 중/실행 중/소비됨)에 따라 연산 가능 여부가 달라짐 |
| **javax.net.ssl.SSLEngine** | Handshake 상태(NOT_HANDSHAKING/NEED_WRAP/NEED_UNWRAP/FINISHED)에 따라 다른 처리 |
| **java.sql.Connection** | Auto-commit 모드, Transaction Isolation Level 상태에 따라 쿼리 실행 행동이 달라짐 |
| **java.util.Iterator** | hasNext() 상태에 따라 next() 가능 여부 결정 → 간단한 상태 머신 |
| **ClassLoader 상태** | 클래스 로딩 → 링킹(검증/준비/해석) → 초기화 단계별 상태 전이 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **TCP 소켓 상태 머신** | LISTEN → SYN_RECEIVED → ESTABLISHED → FIN_WAIT → TIME_WAIT → CLOSED → 가장 대표적인 상태 패턴 |
| **HTTP/2 Stream State** | idle → open → half-closed → closed 상태에 따라 프레임 전송/수신 가능 여부 결정 |
| **TLS Handshake State** | ClientHello → ServerHello → Certificate → KeyExchange → Finished 단계별 상태 전이 |
| **BGP FSM** | Idle → Connect → Active → OpenSent → OpenConfirm → Established 상태에 따라 라우팅 행동 결정 |
| **WiFi 연결 상태** | Disconnected → Scanning → Associating → Authenticating → Connected 상태 전이 |
| **WebSocket Lifecycle** | CONNECTING → OPEN → CLOSING → CLOSED 상태에 따라 메시지 전송 가능 여부 결정 |
| **DHCP Client State** | INIT → SELECTING → REQUESTING → BOUND → RENEWING → REBINDING 상태 전이 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **트랜잭션 상태** | ACTIVE → PARTIALLY_COMMITTED → COMMITTED / FAILED → ABORTED 상태에 따라 연산 가능 여부 결정 |
| **JPA 엔티티 생명주기** | New(Transient) → Managed → Detached → Removed 상태에 따라 영속성 컨텍스트 행동이 달라짐 |
| **Connection Pool 상태** | Available → InUse → Validation → Evicted → Closed 상태에 따라 커넥션 할당/반환 행동 결정 |
| **Redis Cluster Node State** | PFAIL → FAIL → OK 상태에 따라 슬롯 마이그레이션, Failover 결정 |
| **MongoDB Replica Set State** | PRIMARY → SECONDARY → ARBITER → RECOVERING → DOWN 상태에 따라 읽기/쓰기 가능 여부 결정 |
| **PostgreSQL WAL Sender State** | streaming → catchup → sync 상태에 따라 복제 행동이 달라짐 |
| **Elasticsearch Shard State** | UNASSIGNED → INITIALIZING → STARTED → RELOCATING 상태에 따라 검색/인덱싱 가능 여부 결정 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **프로세스 상태** | Created → Ready → Running → Waiting → Terminated → OS 스케줄러의 핵심 상태 머신 |
| **Linux OOM Killer** | 메모리 상태(Normal → Low → Critical)에 따라 프로세스 종료 결정 |
| **systemd Unit State** | inactive → activating → active → deactivating → failed 상태에 따라 서비스 관리 행동 결정 |
| **Docker Container Lifecycle** | created → running → paused → stopped → dead 상태에 따라 컨테이너 조작 가능 여부 결정 |
| **Kubernetes Pod Phase** | Pending → Running → Succeeded / Failed → Unknown 상태에 따라 스케줄링/재시작 결정 |
| **Linux Power State** | S0(Working) → S1(Standby) → S3(Sleep) → S4(Hibernate) → S5(Off) 상태 전이 |
| **Disk I/O Scheduler State** | idle → busy → congested 상태에 따라 I/O 큐잉 전략이 달라짐 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **유한 상태 머신 (FSM)** | 상태 패턴의 이론적 기반. 정규 표현식 엔진, 파서, 게임 AI 등에 활용 |
| **정규 표현식 DFA/NFA** | 문자열 매칭 시 현재 상태에 따라 다음 전이를 결정하는 오토마타 |
| **컴파일러 Lexer/Parser** | 토큰화/구문 분석 시 현재 상태에 따라 다른 파싱 규칙 적용 |
| **전략 패턴과의 차이** | 전략: 알고리즘 교체. 상태: 내부 상태에 따른 행동 변경 + 자동 상태 전이 |
| **Turnstile (회전문) 오토마타** | Locked ↔ Unlocked 상태 전이 → 상태 패턴의 가장 클래식한 교과서 예제 |
| **Markov Chain** | 현재 상태의 확률 분포에 따라 다음 상태로 전이 → 확률적 상태 머신 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Consumer State** | 파티션 할당 → 메시지 소비 → 오프셋 커밋 → 리밸런싱 상태에 따라 소비 행동 결정 |
| **RabbitMQ Channel State** | Open → Closing → Closed 상태에 따라 메시지 발행/소비 가능 여부 결정 |
| **Saga Orchestrator 상태** | STARTED → STEP_N_EXECUTING → STEP_N_COMPENSATING → COMPLETED / FAILED 상태 전이 |
| **Circuit Breaker (Resilience4j)** | CLOSED → OPEN → HALF_OPEN 상태에 따라 호출 허용/차단/시험 결정 |
| **Message Delivery State** | PENDING → SENT → DELIVERED → ACKNOWLEDGED → DEAD_LETTER 상태에 따라 재전송/폐기 결정 |
| **Apache Camel Route State** | Started → Suspended → Stopped 상태에 따라 메시지 라우팅 행동 결정 |
