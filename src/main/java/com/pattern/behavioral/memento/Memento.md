# 메멘토 패턴 (Memento Pattern)

## 패턴 개요
객체의 내부 상태를 외부에 저장해두었다가 필요할 때 복원하는 패턴. 캡슐화를 유지하면서 이전 상태로 되돌리기(Undo) 기능을 구현할 수 있다.

## 특징
- Originator(원본 객체) → 상태를 가진 객체
- Memento(기념품) → 저장된 상태 스냅샷 (불변 객체)
- Caretaker(관리자) → Memento를 보관하고 관리
- 캡슐화를 깨지 않고 객체 상태를 저장/복원

## 장점
- 캡슐화 유지하면서 상태 저장/복원
- Undo/Redo 기능 구현 용이
- 상태 이력 관리 가능

## 단점
- 상태 저장이 빈번하면 메모리 사용량 증가
- 대용량 객체의 스냅샷은 성능 저하 가능

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **@Transactional 롤백** | 트랜잭션 롤백 시 DB 상태를 이전 스냅샷으로 복원 → Savepoint가 메멘토 역할 |
| **Hibernate Dirty Checking** | EntityManager가 엔티티 로딩 시 원본 스냅샷을 1차 캐시에 보관, flush 시점에 비교하여 변경 감지 |
| **Spring Session** | HttpSession 상태를 Redis/JDBC에 직렬화하여 저장하고 필요 시 역직렬화로 복원 |
| **Spring Batch ExecutionContext** | Step/Job 실행 중 상태를 ExecutionContext에 저장, 재시작 시 마지막 체크포인트부터 복원 |
| **Spring WebFlow FlowExecution Snapshot** | 웹 플로우의 각 단계 상태를 스냅샷으로 저장, 브라우저 뒤로가기 시 이전 상태 복원 |
| **Spring Data Envers** | `@Audited` 엔티티의 변경 이력을 Revision 테이블에 자동 저장, 특정 시점 상태 조회/복원 가능 |
| **Spring Cache @CachePut** | 캐시에 객체 상태를 저장해두고 이후 조회 시 저장된 상태를 반환 |
| **Spring Security SecurityContext 저장** | SecurityContextHolder에 인증 상태를 저장, 요청 간 SecurityContext를 HttpSession에 보관/복원 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.io.Serializable** | 객체 상태를 바이트 스트림으로 직렬화하여 저장하고 역직렬화로 복원하는 메멘토의 직접적 구현 |
| **java.util.Stack + Undo** | Stack에 상태를 push하고 undo 시 pop하여 복원 → Caretaker 역할 |
| **java.lang.Object.clone()** | Cloneable 인터페이스로 객체의 얕은 복사본(스냅샷)을 생성 |
| **java.awt.Component.getGraphics()** | Graphics 상태를 저장하고 복원하는 save/restore 메커니즘 |
| **java.util.Date / Calendar** | Date 객체를 복사하여 원본 상태를 보존하는 방어적 복사(Defensive Copy) |
| **javax.swing.undo.UndoManager** | Swing의 Undo/Redo 프레임워크 → UndoableEdit이 메멘토, UndoManager가 Caretaker |
| **java.nio.ByteBuffer.mark() / reset()** | 버퍼의 position을 mark()로 저장하고 reset()으로 복원 |
| **ProcessBuilder.environment()** | 프로세스 환경 변수의 스냅샷을 Map으로 반환 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **TCP Sequence Number** | TCP가 전송 상태(시퀀스 번호, 윈도우 크기)를 기억하고 패킷 손실 시 재전송 복원 |
| **HTTP Cookie / Session** | 서버가 세션 상태를 저장하고, 쿠키 기반으로 클라이언트 상태를 복원 |
| **VPN Reconnect** | VPN 연결 상태(터널 설정, 암호화 키)를 저장하여 끊김 시 자동 재연결 |
| **DNS Cache** | DNS 조회 결과를 TTL 동안 캐시에 저장하여 재조회 없이 복원 |
| **WebSocket Reconnect State** | WebSocket 연결 끊김 시 마지막 메시지 ID를 저장하여 재연결 후 이어서 수신 |
| **TLS Session Resumption** | TLS 핸드셰이크 상태를 세션 티켓/ID로 저장, 재연결 시 풀 핸드셰이크 없이 복원 |
| **BGP Route Snapshot** | BGP 라우팅 테이블의 스냅샷을 저장하여 장애 시 이전 라우팅 경로로 복원 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **MVCC (Multi-Version Concurrency Control)** | PostgreSQL/MySQL InnoDB가 행의 여러 버전(스냅샷)을 유지하여 트랜잭션 격리 |
| **Undo Log (InnoDB)** | DML 실행 전 원본 데이터를 Undo Log에 저장, 롤백 시 복원 → DB 레벨의 메멘토 |
| **Redo Log / WAL** | 커밋된 변경을 Redo Log에 기록, 장애 복구 시 재적용하여 상태 복원 |
| **Point-in-Time Recovery (PITR)** | 특정 시점의 DB 상태를 백업 + binlog/WAL 재생으로 복원 |
| **Database Snapshot (SQL Server)** | Copy-on-Write 방식으로 DB의 읽기 전용 스냅샷 생성 |
| **Redis RDB Snapshot** | Redis 메모리 상태를 디스크에 RDB 파일로 스냅샷 저장, 재시작 시 복원 |
| **Event Sourcing Store** | 상태 변경을 이벤트로 저장하고, 이벤트 재생으로 임의 시점 상태 복원 → 메멘토의 이벤트 기반 확장 |
| **JPA @Version (Optimistic Lock)** | 엔티티 버전을 저장하여 동시 수정 감지, 충돌 시 이전 상태 기준으로 판단 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Git Commit** | 파일 시스템의 스냅샷을 커밋으로 저장하고 `git checkout`/`git reset`으로 복원 → 가장 대표적인 메멘토 |
| **VM Snapshot (VMware/VirtualBox)** | 가상 머신의 메모리+디스크+CPU 상태를 스냅샷으로 저장, 언제든 복원 |
| **Docker Image Layer** | 각 Dockerfile 명령이 레이어(스냅샷)로 저장, 이전 레이어 기반으로 복원/재빌드 |
| **시스템 복원 (Windows) / Time Machine (macOS)** | OS 전체 상태를 특정 시점으로 복원 |
| **프로세스 체크포인팅 (CRIU)** | 실행 중인 프로세스의 메모리, 레지스터, 파일 디스크립터를 저장하고 복원 |
| **Kubernetes etcd Snapshot** | 클러스터 상태를 etcd 스냅샷으로 저장, 클러스터 복구 시 사용 |
| **ZFS / Btrfs Snapshot** | Copy-on-Write 파일시스템의 즉시 스냅샷 생성 및 롤백 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Command + Memento (Undo/Redo)** | Command 패턴과 결합하여 실행 전 상태를 메멘토로 저장, Undo 시 복원 |
| **Persistent Data Structure** | 변경 시 이전 버전을 유지하는 불변 자료구조 (Clojure Vector, Scala List) → 구조적 메멘토 |
| **백트래킹 알고리즘** | DFS/N-Queen 등에서 현재 상태를 저장하고, 조건 불만족 시 이전 상태로 복원 |
| **트랜잭셔널 메모리 (STM)** | 메모리 변경을 트랜잭션으로 묶고 충돌 시 원래 상태로 롤백 |
| **Copy-on-Write (COW)** | 쓰기 시점에만 복사본을 만들어 원본 보존 → 효율적인 스냅샷 전략 |
| **Dynamic Programming 메모이제이션** | 부분 문제의 결과를 저장하여 재사용 → 계산 상태의 메멘토 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Consumer Offset** | Consumer가 마지막 읽은 오프셋을 저장, 재시작 시 해당 오프셋부터 이어서 소비 → 소비 상태의 메멘토 |
| **RabbitMQ Message Acknowledgement** | 메시지 처리 상태를 ACK/NACK로 관리, 미처리 메시지는 큐에 보존 |
| **Apache Flink Checkpoint** | 스트림 처리 상태를 주기적으로 체크포인트로 저장, 장애 시 마지막 체크포인트에서 복원 |
| **Apache Spark RDD Lineage** | RDD의 변환 과정을 Lineage로 기록, 파티션 손실 시 Lineage 재생으로 복원 |
| **Saga 보상 트랜잭션** | 분산 트랜잭션의 각 단계 상태를 저장, 실패 시 보상 액션으로 이전 상태 복원 |
| **Event Store (Axon Framework)** | 도메인 이벤트를 순서대로 저장, Aggregate 상태를 이벤트 재생으로 복원 |
