# 옵저버 패턴 (Observer Pattern)

## 패턴 개요
객체의 상태 변화를 관찰하는 옵저버 목록을 관리하고, 변화가 발생하면 자동으로 통지하는 패턴. 발행-구독(Pub-Sub) 모델의 기반이 된다.

## 특징
- Subject(발행자)가 상태 변경 시 모든 Observer(구독자)에게 자동 통지
- 느슨한 결합 → Subject는 Observer의 구체 타입을 알 필요 없음
- 1:N 의존 관계 (하나의 Subject에 여러 Observer)
- Push 모델 (Subject가 데이터 전달) vs Pull 모델 (Observer가 데이터 조회)

## 장점
- 개방-폐쇄 원칙(OCP) → 새 Observer 추가 용이
- 런타임에 구독/해제 가능
- 느슨한 결합으로 유연한 확장

## 단점
- 통지 순서를 보장하기 어려움
- 순환 의존성 발생 가능
- Observer가 많으면 성능 저하

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **ApplicationEvent / @EventListener** | Spring 이벤트 시스템의 핵심. `ApplicationEventPublisher`가 Subject, `@EventListener` 메서드가 Observer |
| **@TransactionalEventListener** | 트랜잭션 커밋/롤백 시점에 이벤트를 비동기 처리 → 트랜잭션 상태 변화의 옵저버 |
| **Spring WebSocket STOMP** | 클라이언트가 토픽을 구독(subscribe)하고 서버 이벤트 발생 시 자동 통지 |
| **Spring Cloud Bus** | Config Server 변경 이벤트를 `/actuator/bus-refresh`로 모든 MSA 인스턴스에 전파 |
| **Spring Boot ApplicationListener** | `ApplicationStartedEvent`, `ContextRefreshedEvent` 등 라이프사이클 이벤트를 구독 |
| **Spring Data @DomainEvents** | Aggregate Root에서 도메인 이벤트를 발행, Repository save 시점에 자동 발행 |
| **Spring Reactor Flux/Mono** | 리액티브 스트림이 옵저버 패턴의 비동기/논블로킹 확장 → Subscriber가 Publisher를 구독 |
| **Spring Integration Channel Interceptor** | 메시지 채널에 인터셉터를 등록하여 메시지 흐름 이벤트를 감지 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.beans.PropertyChangeListener** | Bean 속성 변경 시 등록된 리스너에게 PropertyChangeEvent 통지 → JavaBeans 표준 옵저버 |
| **java.util.concurrent.Flow (JDK 9+)** | Reactive Streams 표준 → `Flow.Publisher`, `Flow.Subscriber`가 옵저버 패턴의 표준화 |
| **javax.swing.event.ChangeListener** | Swing UI 컴포넌트 상태 변경 감지 → 버튼 클릭, 슬라이더 변경 등 |
| **java.nio.file.WatchService** | 파일시스템 디렉토리 변경(생성/수정/삭제)을 감시하는 옵저버 |
| **java.util.EventListener** | 모든 AWT/Swing 이벤트 리스너의 최상위 마커 인터페이스 |
| **java.lang.instrument.ClassFileTransformer** | 클래스 로딩 이벤트를 감시하여 바이트코드 변환 수행 |
| **java.util.logging.Handler** | Logger에 Handler를 등록하여 로그 이벤트를 관찰 → ConsoleHandler, FileHandler |
| **java.util.ServiceLoader** | SPI를 통해 등록된 서비스 구현체를 발견 → 간접적인 구독 메커니즘 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **WebSocket** | 서버-클라이언트 간 양방향 연결에서 서버 이벤트를 클라이언트에 실시간 Push |
| **Server-Sent Events (SSE)** | 서버 → 클라이언트 단방향 이벤트 스트림. HTTP/2 위에서 실시간 통지 |
| **HTTP Webhook** | 이벤트 발생 시 등록된 URL로 HTTP POST 콜백 → 서비스 간 옵저버 패턴 |
| **MQTT (IoT 프로토콜)** | 경량 Pub/Sub 프로토콜. IoT 디바이스가 토픽을 구독하고 이벤트 수신 |
| **gRPC Server Streaming** | 서버가 스트림으로 클라이언트에게 연속적인 응답을 Push |
| **DNS Notify (RFC 1996)** | Primary DNS 서버가 Zone 변경 시 Secondary 서버에 NOTIFY 메시지로 통지 |
| **IGMP (Internet Group Management Protocol)** | 멀티캐스트 그룹 가입/탈퇴를 관리 → 그룹 멤버가 멀티캐스트 이벤트를 수신 |
| **Long Polling** | 클라이언트가 서버에 대기 요청을 보내고 이벤트 발생 시 응답 → WebSocket 이전의 실시간 통지 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **PostgreSQL LISTEN / NOTIFY** | 채널 기반 Pub/Sub → `LISTEN channel_name`으로 구독, `NOTIFY`로 이벤트 발행 |
| **MySQL Binlog Replication** | Master의 변경 이벤트를 binlog에 기록, Slave가 binlog를 구독하여 복제 |
| **Change Data Capture (CDC)** | Debezium 등이 DB 변경 이벤트를 캡처하여 Kafka로 발행 → DB 레벨 옵저버 |
| **Redis Pub/Sub** | 채널 기반 메시지 발행/구독. `SUBSCRIBE`로 채널 구독, `PUBLISH`로 이벤트 통지 |
| **Redis Keyspace Notification** | 키 만료/삭제/변경 이벤트를 구독하여 감지 |
| **MongoDB Change Stream** | MongoDB의 oplog를 기반으로 컬렉션 변경 이벤트를 실시간 스트리밍 |
| **JPA EntityListener (@PrePersist, @PostUpdate)** | 엔티티 생명주기 이벤트를 감지하여 콜백 실행 |
| **Hibernate Interceptor** | Session 레벨에서 엔티티 CRUD 이벤트를 감시하여 가로채기 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Linux inotify** | 파일시스템 이벤트(생성/수정/삭제/이동)를 커널 레벨에서 감시하고 통지 |
| **epoll / kqueue / IOCP** | I/O 이벤트 준비 완료를 커널이 애플리케이션에 통지 → 이벤트 기반 I/O 멀티플렉싱 |
| **UNIX Signal** | SIGINT, SIGTERM, SIGHUP 등 시그널 발생 시 등록된 핸들러에 통지 |
| **D-Bus Signal** | Linux IPC에서 프로세스 간 이벤트를 시그널로 발행/구독 |
| **systemd Notification** | 서비스 상태 변경(시작/중지/실패)을 systemd에 통지 → sd_notify() |
| **Kubernetes Watch API** | `kubectl get pods --watch`처럼 리소스 변경 이벤트를 실시간 스트리밍 |
| **Prometheus Alertmanager** | 메트릭 임계값 초과 시 등록된 채널(Slack/PagerDuty/Email)로 알림 통지 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **이벤트 루프 (Event Loop)** | Node.js, Netty, Vert.x의 이벤트 루프가 I/O 이벤트를 감지하여 등록된 핸들러에 통지 |
| **Pub-Sub 패턴** | 옵저버 패턴의 확장. 토픽/채널을 통해 발행자와 구독자를 더 느슨하게 분리 |
| **Reactive Streams** | Publisher → Subscriber → Subscription → Processor 체인으로 비동기 데이터 흐름 관찰 |
| **데이터 바인딩 (MVVM)** | ViewModel의 속성 변경을 View가 자동 감지하여 UI 업데이트 → 양방향 옵저버 |
| **Observable Collection** | 컬렉션 변경(add/remove)을 감지하여 UI 갱신 (JavaFX ObservableList) |
| **콜백 함수 (Callback)** | 특정 이벤트 완료 시 등록된 콜백 함수를 호출 → 옵저버 패턴의 가장 단순한 형태 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Apache Kafka Consumer Group** | Consumer가 토픽을 구독하고 Producer의 이벤트를 소비 → 분산 옵저버 패턴 |
| **RabbitMQ Fanout Exchange** | Exchange가 바인딩된 모든 큐에 메시지를 브로드캐스트 → 1:N 통지 |
| **Apache Pulsar** | 토픽 기반 Pub/Sub + 커서 기반 구독 관리. 멀티 테넌시 지원 |
| **Redis Streams (XREAD)** | 스트림 기반 메시지 소비. Consumer Group으로 분산 구독 |
| **AWS SNS (Simple Notification Service)** | 토픽에 여러 구독(SQS, Lambda, HTTP, Email)을 등록하여 팬아웃 통지 |
| **ZeroMQ PUB-SUB** | 경량 메시징에서 PUB 소켓이 SUB 소켓에 메시지를 멀티캐스트 |
| **Spring Cloud Stream** | 바인더(Kafka/RabbitMQ)를 추상화하여 메시지 발행/구독을 선언적으로 처리 |
