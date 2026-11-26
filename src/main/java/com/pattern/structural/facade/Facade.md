# 퍼사드 패턴 (Facade Pattern)

## 패턴 개요
복잡한 서브시스템들의 인터페이스를 하나의 통합된 간단한 인터페이스로 제공하는 패턴. 클라이언트가 복잡한 내부 구조를 모르고도 쉽게 사용할 수 있게 한다.

## 특징
- 복잡한 서브시스템을 단순한 인터페이스로 감싸기
- 서브시스템에 대한 진입점(Entry Point) 역할
- 서브시스템 간의 의존성을 최소화
- 서브시스템 직접 접근도 여전히 가능 (강제하지 않음)

## 장점
- 서브시스템 복잡도를 클라이언트로부터 숨김
- 느슨한 결합 (클라이언트 ↔ 서브시스템)
- 계층화된 구조 설계 용이

## 단점
- 퍼사드가 모든 기능을 갖는 God Object가 될 위험
- 서브시스템의 세밀한 제어가 어려울 수 있음

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **JdbcTemplate** | JDBC의 Connection → PreparedStatement → ResultSet → close() 복잡한 처리를 단순 메서드(`queryForList`, `update`)로 제공 |
| **RestTemplate / WebClient** | HTTP 커넥션 관리, 직렬화/역직렬화, 에러 처리를 단순한 `getForObject()`, `postForEntity()`로 제공 |
| **Spring Boot AutoConfiguration** | 복잡한 Bean 설정/조건부 생성/프로퍼티 바인딩을 `@SpringBootApplication` 하나로 자동 처리 |
| **Spring Boot Starter** | 여러 라이브러리 의존성 + 설정을 하나의 Starter(`spring-boot-starter-web`)로 묶어 제공 |
| **Spring Data Repository** | EntityManager/Session/Template의 복잡한 CRUD를 `save()`, `findById()` 등 단순 메서드로 제공 |
| **Spring Security** | 인증, 권한, 세션, CSRF, CORS 등 복잡한 보안 처리를 `HttpSecurity` 설정 하나로 통합 |
| **Spring Batch JobLauncher** | 복잡한 배치 실행(Job/Step/Chunk/Reader/Processor/Writer)을 `run(job, parameters)` 하나로 실행 |
| **@Transactional** | Connection 획득 → 트랜잭션 시작 → 커밋/롤백 → 커넥션 반환을 어노테이션 하나로 처리 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.net.URL** | 소켓 연결, 프로토콜 핸들링, 스트림 관리를 `openStream()` 하나로 단순화 |
| **javax.faces.context.FacesContext** | JSF의 여러 서브시스템(Request, Response, Application)을 하나의 Context로 통합 |
| **java.util.concurrent.Executors** | ThreadPool 생성의 복잡한 파라미터를 `newFixedThreadPool(n)` 등으로 단순화 |
| **java.lang.Class** | 리플렉션의 복잡한 처리를 `getMethod()`, `newInstance()` 등으로 단순화 |
| **Files (NIO.2)** | 파일 I/O의 복잡한 스트림/채널 처리를 `readAllLines()`, `write()` 등 유틸로 단순화 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **API Gateway** | 여러 마이크로서비스를 하나의 진입점으로 통합. 인증/라우팅/로깅/Rate Limiting을 한 곳에서 처리 |
| **BFF (Backend For Frontend)** | 프론트엔드별로 여러 서비스를 조합하여 최적화된 단일 API를 제공 |
| **SDK / Client Library** | AWS SDK, Google Cloud Client 등이 복잡한 REST/gRPC 호출을 단순 메서드로 제공 |
| **DNS** | 복잡한 IP 주소 체계를 도메인 이름이라는 단순한 인터페이스로 감싸는 퍼사드 |
| **Load Balancer** | 여러 서버 인스턴스의 주소/상태/가중치 관리를 하나의 VIP(가상 IP)로 단순화 |
| **CDN** | 오리진 서버, 엣지 캐시, SSL, 압축 등 복잡한 처리를 하나의 도메인으로 제공 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Data JPA** | EntityManager의 복잡한 persist/merge/find/createQuery를 Repository 인터페이스로 단순화 |
| **MyBatis** | JDBC의 복잡한 처리(Connection, PreparedStatement, ResultSet)를 XML/어노테이션 매핑으로 단순화 |
| **Hibernate Session** | JDBC 위에 ORM 기능(영속성 관리, 캐시, 지연 로딩, Dirty Checking)을 Session 하나로 제공 |
| **Redis Template** | Redis 커맨드(SETEX, HGET, LPUSH 등)를 `opsForValue()`, `opsForHash()` 등으로 단순화 |
| **Elasticsearch RestHighLevelClient** | 복잡한 HTTP 요청/JSON 파싱을 `index()`, `search()`, `bulk()` 메서드로 단순화 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **시스템 콜 래퍼** | POSIX API가 커널의 복잡한 처리(메모리 관리, I/O 스케줄링)를 `read()`, `write()`, `open()` 등으로 단순화 |
| **Docker CLI** | 컨테이너 런타임, 이미지 레이어, 네트워크, 볼륨 관리를 `docker run` 하나로 단순화 |
| **kubectl** | Kubernetes API Server의 복잡한 REST API를 `kubectl apply -f` 등 CLI로 단순화 |
| **SLF4J** | 다양한 로깅 프레임워크(Logback, Log4j2, JUL)를 `log.info()` 하나의 API로 통합 |
| **package manager (apt, yum, brew)** | 복잡한 의존성 해결/다운로드/설치를 `apt install` 하나로 단순화 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Collections 유틸리티** | `Collections.sort()`, `Collections.max()` → 복잡한 정렬/탐색 알고리즘을 단순 메서드로 제공 |
| **Arrays 유틸리티** | `Arrays.sort()`, `Arrays.binarySearch()` → 배열 조작을 단순화 |
| **Guava 유틸리티** | `Strings.isNullOrEmpty()`, `Lists.partition()`, `Maps.newHashMap()` 등 복잡한 처리를 단순 메서드로 |
| **Apache Commons Lang** | `StringUtils`, `NumberUtils`, `DateUtils` → 복잡한 null 체크/변환/포맷팅을 단순화 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **KafkaTemplate** | Kafka Producer의 복잡한 직렬화/파티셔닝/배치/콜백 처리를 `send(topic, message)` 하나로 단순화 |
| **RabbitTemplate** | RabbitMQ의 Exchange/Queue/Binding/Channel 관리를 `convertAndSend()` 하나로 단순화 |
| **Spring Cloud Stream** | 메시지 브로커의 복잡한 설정을 `@StreamListener`와 application.yml 설정으로 단순화 |
