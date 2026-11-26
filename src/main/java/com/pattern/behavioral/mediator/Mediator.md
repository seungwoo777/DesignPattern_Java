# 중재자 패턴 (Mediator Pattern)

## 패턴 개요
객체들이 서로 직접 참조하지 않고 중재자를 통해 소통하는 패턴. 객체 간 M:N 관계를 1:N으로 단순화하여 결합도를 줄인다.

## 특징
- 객체 간의 복잡한 M:N 관계를 중재자를 통해 1:N으로 단순화
- 각 객체(Colleague)는 중재자만 알고, 다른 객체를 직접 참조하지 않음
- 상호작용 로직이 중재자에 집중됨
- 이벤트 버스, 메시지 브로커 개념과 유사

## 장점
- 객체 간 결합도 감소
- 단일 책임 원칙(SRP) → 통신 로직을 중재자에 집중
- 새로운 Colleague 추가 용이

## 단점
- 중재자가 God Object가 될 위험
- 중재자에 로직이 집중되면 복잡도 증가

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **DispatcherServlet** | Spring MVC의 중심 중재자. HandlerMapping, HandlerAdapter, ViewResolver, ExceptionResolver 등 여러 컴포넌트 간의 상호작용을 조율 |
| **ApplicationEventPublisher** | 이벤트 발행/구독을 중재. `@EventListener`가 등록된 빈들에게 이벤트를 전달 |
| **Spring Integration MessageChannel** | 메시지 생산자와 소비자 사이의 중재자 역할. DirectChannel, QueueChannel 등 |
| **Spring Cloud Gateway RoutePredicate** | 여러 Predicate/Filter 간의 조합을 Route가 중재 |
| **Spring Batch JobRepository** | Job, Step, Execution 간의 상태 관리를 중재 |
| **Spring WebFlux DispatcherHandler** | 리액티브 환경에서 HandlerMapping, HandlerAdapter, ResultHandler를 중재 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.util.Timer** | TimerTask들의 스케줄링을 Timer가 중재 |
| **java.util.concurrent.Executor** | 작업 제출자(Runnable)와 실행 스레드 사이를 ExecutorService가 중재 |
| **java.lang.reflect.Method** | 호출자와 대상 메서드 사이를 리플렉션 API가 중재 |
| **java.awt.Dialog (모달 다이얼로그)** | 부모 창과 다이얼로그 간의 데이터 전달을 Dialog가 중재 |
| **java.util.concurrent.CountDownLatch** | 여러 스레드의 완료를 대기하는 동기화 중재자 |
| **java.util.concurrent.CyclicBarrier** | 여러 스레드가 특정 지점에서 만나도록 중재 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **메시지 브로커 (Kafka/RabbitMQ)** | 서비스 간 직접 통신 대신 브로커가 메시지를 중재 → MSA에서 느슨한 결합의 핵심 |
| **API Gateway** | 클라이언트와 마이크로서비스 간의 통신을 중재. 라우팅, 인증, Rate Limiting |
| **DNS** | 클라이언트와 서버 간의 주소 변환을 중재. 클라이언트는 도메인만 알면 됨 |
| **로드밸런서** | 클라이언트와 서버 인스턴스 간의 트래픽 분배를 중재 |
| **TURN/STUN 서버 (WebRTC)** | P2P 통신이 불가능할 때 TURN 서버가 미디어 스트림을 중재 |
| **SMTP Relay** | 메일 발송자와 수신자 사이의 메일 전달을 중재 |
| **Service Mesh Control Plane** | Istio의 Pilot이 서비스 간 통신 규칙을 중재 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **트랜잭션 매니저 (2PC)** | 분산 트랜잭션에서 여러 리소스 매니저 간의 커밋/롤백을 중재 (Two-Phase Commit) |
| **Connection Pool Manager** | 애플리케이션과 DB 사이의 커넥션 할당/반환을 중재 |
| **Query Optimizer** | SQL 파서, 통계 정보, 실행 엔진 간의 쿼리 실행 계획 결정을 중재 |
| **JPA Persistence Context** | 엔티티, DB, 캐시 간의 상태 동기화를 영속성 컨텍스트가 중재 |
| **Redis Sentinel** | Master/Slave 간의 Failover를 Sentinel이 중재 |
| **MongoDB Router (mongos)** | 클라이언트와 샤드 간의 쿼리 라우팅을 중재 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **운영체제 커널** | 프로세스, 메모리, I/O, 네트워크 간의 리소스 할당을 커널이 중재 → 가장 거대한 중재자 |
| **Window Manager** | 여러 애플리케이션 창의 포커스, 배치, 이벤트 전달을 중재 |
| **systemd** | 여러 서비스의 시작/종료/의존성을 중재 |
| **D-Bus** | Linux 데스크톱에서 프로세스 간 통신(IPC)을 중재하는 메시지 버스 |
| **Kubernetes API Server** | etcd, Scheduler, Controller, Kubelet 간의 상호작용을 API Server가 중재 |
| **Air Traffic Control** | 비행기 간 직접 통신 대신 관제탑이 중재 → 중재자 패턴의 가장 직관적인 비유 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **이벤트 버스 / 이벤트 루프** | 이벤트 생산자와 소비자 사이를 이벤트 루프가 중재 (Node.js, Netty, Vert.x) |
| **Pub-Sub 패턴** | 발행자와 구독자가 직접 참조하지 않고 토픽/채널이 중재 |
| **Observer와의 차이** | Observer: Subject가 Observer를 직접 알고 통지. Mediator: 중재자가 모든 통신을 집중 관리 |
| **Blackboard 패턴** | 여러 에이전트가 공유 Blackboard(중재자)를 통해 간접적으로 협업 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Apache Kafka** | Producer와 Consumer 사이에서 토픽 기반으로 메시지를 중재하는 대표적 중재자 |
| **RabbitMQ** | Exchange가 Producer와 Queue 사이의 라우팅을 중재 |
| **Apache ZooKeeper** | 분산 시스템의 Configuration, Naming, Synchronization을 중재하는 코디네이션 서비스 |
| **Redis Pub/Sub** | 채널 기반으로 발행자와 구독자 사이의 메시지를 중재 |
| **Saga Orchestrator** | 분산 트랜잭션에서 여러 서비스의 실행/보상을 Orchestrator가 중재 |
