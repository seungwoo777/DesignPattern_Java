# 템플릿 메서드 패턴 (Template Method Pattern)

## 패턴 개요
알고리즘의 전체 흐름(뼈대)은 상위 클래스에서 정의하고, 각 단계의 구체적 구현은 하위 클래스에서 담당하는 패턴

## 특징
- 상위 클래스의 `final` 메서드가 알고리즘 흐름(템플릿)을 정의
- 하위 클래스가 각 단계(Hook)를 오버라이드하여 구현
- "할리우드 원칙(Hollywood Principle)" → 상위 클래스가 하위 클래스를 호출 (Don't call us, we'll call you)
- 전략 패턴과 유사하지만 상속 기반 vs 합성 기반의 차이

## 장점
- 코드 중복 제거 → 공통 흐름은 상위 클래스에서 한 번만 작성
- 알고리즘 구조 유지 → 하위 클래스는 세부만 변경
- 확장 용이 → 새 하위 클래스 추가만으로 변형 가능

## 단점
- 상속 기반이라 유연성이 합성보다 떨어짐
- 상위 클래스 변경 시 모든 하위 클래스에 영향
- 리스코프 치환 원칙(LSP) 위반 가능성

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **JdbcTemplate** | 커넥션 획득 → SQL 실행 → 결과 매핑 → 리소스 해제 흐름은 고정, `RowMapper`/`PreparedStatementSetter`만 개발자가 구현 |
| **RestTemplate / WebClient** | HTTP 요청 구성 → 전송 → 응답 처리 → 에러 핸들링 흐름 고정, 요청/응답 커스터마이징만 변경 |
| **AbstractController / FrameworkServlet** | 요청 처리의 전체 흐름(`service()` → `doDispatch()`)을 정의하고, 세부 처리만 하위 클래스에서 구현 |
| **Spring Security AbstractAuthenticationProcessingFilter** | 인증 흐름(요청감지→인증시도→성공/실패처리)을 템플릿으로 정의, `attemptAuthentication()`만 구현 |
| **AbstractTransactionalJUnit4SpringContextTests** | 테스트 실행 흐름(컨텍스트로드→트랜잭션시작→테스트실행→롤백)을 템플릿으로 정의 |
| **Spring Batch AbstractStep** | 배치 Step의 실행 흐름(open→execute→close)을 템플릿으로 정의, `doExecute()`만 구현 |
| **Spring Data SimpleJpaRepository** | CRUD의 기본 흐름을 제공하고, 커스텀 구현은 `@Query`나 메서드 이름 규칙으로 확장 |
| **Spring AOP AbstractPointcutAdvisor** | Advice 적용 흐름을 템플릿으로 정의, Pointcut 매칭 로직만 하위에서 구현 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **HttpServlet** | `service()` 메서드가 HTTP 메서드를 판별하여 `doGet()`/`doPost()`/`doPut()`/`doDelete()` 호출 → 가장 대표적인 템플릿 메서드 |
| **java.util.AbstractList / AbstractMap** | `get(int)`, `size()` 등 핵심 메서드만 구현하면 나머지(iterator, indexOf, subList 등)가 자동 동작 |
| **java.io.InputStream / OutputStream** | `read()` / `write()` 추상 메서드만 구현하면 `read(byte[])` 등 편의 메서드가 동작 |
| **java.util.Collections.sort()** | TimSort 알고리즘의 흐름은 고정, 비교 로직만 `Comparable.compareTo()`로 위임 |
| **java.lang.ClassLoader** | `loadClass()`가 위임 모델(부모 → 자신)의 흐름을 정의, `findClass()`만 하위에서 구현 |
| **javax.servlet.GenericServlet** | 서블릿 초기화/서비스/종료 흐름을 정의, `service()`만 하위에서 구현 |
| **java.util.concurrent.AbstractExecutorService** | `submit()`, `invokeAll()` 등의 흐름을 정의, `execute()`만 하위에서 구현 |
| **java.io.Reader / Writer** | 문자 스트림의 읽기/쓰기 템플릿 흐름을 정의 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **TCP 3-Way Handshake** | SYN → SYN-ACK → ACK 흐름은 고정, 각 단계의 파라미터(시퀀스 번호, 윈도우 크기)만 달라짐 |
| **TLS Handshake** | ClientHello → ServerHello → Certificate → KeyExchange → Finished 흐름 고정, 암호화 스위트만 교체 |
| **HTTP Request/Response Cycle** | 요청 파싱 → 라우팅 → 미들웨어 체인 → 핸들러 실행 → 응답 직렬화 흐름 고정 |
| **OAuth2 Authorization Code Flow** | 인가코드 요청 → 리다이렉트 → 토큰 교환 → 리소스 접근 흐름 고정, 프로바이더별 세부만 다름 |
| **SMTP 프로토콜 흐름** | HELO → MAIL FROM → RCPT TO → DATA → QUIT 순서 고정, 각 단계의 데이터만 다름 |
| **DNS 쿼리 해석 흐름** | 로컬캐시 → 재귀서버 → 루트 → TLD → 권한 서버 순서 고정 |
| **gRPC 호출 흐름** | 채널 획득 → 요청 직렬화 → 전송 → 응답 역직렬화 → 반환 흐름 고정 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JDBC 쿼리 실행 흐름** | Connection 획득 → PreparedStatement 생성 → 파라미터 바인딩 → 실행 → ResultSet 처리 → 리소스 해제 |
| **MyBatis Executor** | 쿼리 실행 흐름(캐시확인→쿼리생성→실행→결과매핑)을 BaseExecutor가 정의, SimpleExecutor/ReuseExecutor가 세부 구현 |
| **Hibernate Session.flush()** | Dirty Checking → SQL 생성 → 순서 결정 → 배치 실행 흐름 고정 |
| **Spring Data Repository** | findAll → 쿼리생성 → 실행 → 결과매핑 흐름 고정, 쿼리 메서드 이름 규칙으로 세부만 변경 |
| **DB Migration (Flyway/Liquibase)** | 버전확인 → 마이그레이션 발견 → 순서정렬 → 실행 → 기록 흐름 고정 |
| **ETL 파이프라인** | Extract → Transform → Load 흐름 고정, 각 단계의 세부 로직만 하위에서 구현 |
| **Connection Pool 획득 흐름** | 풀 확인 → 가용 커넥션 탐색 → 유효성 검증 → 반환 or 생성 흐름 고정 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **부팅 프로세스** | POST → 부트로더 → 커널 로드 → init/systemd → 서비스 시작 흐름 고정, 각 단계 구현만 다름 |
| **시스템 콜 처리** | 모드 전환 → 파라미터 검증 → 핸들러 실행 → 결과 반환 → 모드 복귀 흐름 고정 |
| **패킷 처리 (Linux Netfilter)** | PREROUTING → INPUT/FORWARD → OUTPUT → POSTROUTING 체인 흐름 고정, 규칙만 변경 |
| **JVM 클래스 로딩** | Loading → Linking(Verification/Preparation/Resolution) → Initialization 흐름 고정 |
| **Docker 빌드 프로세스** | Dockerfile 파싱 → 레이어별 빌드 → 캐시 확인 → 이미지 생성 흐름 고정 |
| **CI/CD 파이프라인** | Checkout → Build → Test → Deploy 흐름 고정, 각 단계의 스크립트만 프로젝트별로 다름 |
| **Linux Init System** | SysVinit / Upstart / systemd 모두 서비스 시작 흐름이라는 템플릿은 같고, 세부 구현이 다름 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **분할 정복 (Divide and Conquer)** | 분할 → 정복 → 병합 흐름 고정, 각 단계의 세부 구현만 다름 (MergeSort, QuickSort, Binary Search) |
| **동적 프로그래밍 프레임워크** | 부분 문제 정의 → 점화식 → 메모이제이션/타뷸레이션 → 최적해 추출 흐름 고정 |
| **그래프 탐색 (DFS/BFS)** | 방문 → 인접 노드 탐색 → 처리 흐름 고정, 방문 순서(Stack/Queue)와 처리 로직만 다름 |
| **Builder 패턴과의 비교** | 둘 다 단계별 구축. 템플릿 메서드: 상속으로 단계 구현. Builder: 합성으로 단계별 구성 |
| **Map-Reduce** | Map → Shuffle → Reduce 흐름 고정, Mapper와 Reducer 구현만 개발자가 제공 |
| **컴파일러 파이프라인** | 어휘분석 → 구문분석 → 의미분석 → 최적화 → 코드생성 흐름 고정, 각 단계만 언어별로 구현 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Consumer Poll Loop** | poll() → 레코드 처리 → 오프셋 커밋 → 에러 핸들링 흐름 고정, 처리 로직만 개발자가 구현 |
| **Spring Kafka @KafkaListener 흐름** | 메시지 수신 → 역직렬화 → 필터링 → 리스너 호출 → ACK 흐름 고정, 리스너 메서드만 구현 |
| **RabbitMQ MessageListenerContainer** | 연결 → 채널 생성 → 큐 구독 → 메시지 처리 → ACK 흐름 고정 |
| **Apache Camel RouteBuilder** | from() → process() → to() 라우트 흐름을 템플릿으로 정의, 각 프로세서만 구현 |
| **Spring Integration MessageHandler** | 메시지 수신 → 변환 → 처리 → 출력 흐름 고정, 핸들링 로직만 구현 |
| **Batch Message Processing** | 메시지 수집 → 배치 구성 → 일괄 처리 → 결과 발행 흐름 고정, 처리 로직만 변경 |
