# 싱글톤 패턴 (Singleton Pattern)

## 패턴 개요
클래스의 인스턴스가 오직 하나만 생성되도록 보장하고, 전역적인 접근점을 제공하는 패턴

## 특징
- 인스턴스가 하나만 존재함을 보장
- 전역 접근점 제공 (어디서든 동일 인스턴스 참조)
- 지연 초기화(Lazy Initialization) 가능
- 멀티스레드 환경에서 동기화 처리 필요
- 구현 방식: Eager, Lazy, Double-Checked Locking, Bill Pugh(내부 정적 클래스), Enum

## 장점
- 메모리 절약 (인스턴스 재사용)
- 데이터 공유 용이 (전역 상태 관리)
- 인스턴스 생성 비용이 큰 경우 효과적

## 단점
- 테스트 어려움 (전역 상태로 인한 의존성, Mocking 불편)
- 단일 책임 원칙(SRP) 위반 가능성 (생성 + 비즈니스 로직)
- 멀티스레드 환경에서 동기화 이슈
- 상속이 어렵고, 의존성 주입(DI)과 충돌 가능

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Bean (기본 Scope)** | Spring 컨테이너의 Bean은 기본적으로 `singleton` Scope → 애플리케이션 전체에서 하나의 인스턴스만 관리. `@Service`, `@Controller`, `@Repository`, `@Component` 모두 싱글톤 |
| **ApplicationContext** | Spring의 IoC 컨테이너 자체가 싱글톤으로 관리됨. `AnnotationConfigApplicationContext`, `ClassPathXmlApplicationContext` 등 |
| **@Configuration + CGLIB** | `@Configuration` 클래스 내 `@Bean` 메서드는 CGLIB 프록시를 통해 싱글톤 보장. 같은 `@Bean` 메서드를 여러 번 호출해도 동일 인스턴스 반환 |
| **Spring Security SecurityContextHolder** | 인증 정보를 ThreadLocal 싱글톤으로 관리. `SecurityContextHolder.getContext()` |
| **Spring Boot AutoConfiguration** | 각종 AutoConfiguration이 생성하는 Bean들(DataSource, ObjectMapper, RestTemplate 등)이 싱글톤으로 등록 |
| **Environment** | `Environment` 객체가 싱글톤으로 프로퍼티/프로파일 정보를 관리 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.lang.Runtime** | `Runtime.getRuntime()` → JVM 런타임 환경을 단일 인스턴스로 관리 (메모리, GC, 프로세스 실행) |
| **java.awt.Desktop** | `Desktop.getDesktop()` → OS 데스크톱 환경을 단일 인스턴스로 관리 |
| **java.lang.System** | `System.out`, `System.err`, `System.in` → 표준 입출력 스트림이 싱글톤 |
| **Enum Singleton** | Java enum은 JVM이 인스턴스를 하나만 보장 → 가장 안전한 싱글톤 구현 (직렬화/리플렉션 공격 방어) |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **Connection Pool (HikariCP, DBCP)** | 커넥션 풀 매니저가 싱글톤으로 풀을 관리. 애플리케이션 전체에서 하나의 풀 인스턴스 공유 |
| **HTTP Client (OkHttp, Apache HttpClient)** | HTTP 클라이언트 인스턴스를 싱글톤으로 재사용 → 커넥션 풀, 소켓 재활용으로 성능 최적화 |
| **DNS Resolver 캐시** | JVM의 `InetAddress` 캐시가 싱글톤으로 DNS 조회 결과를 캐싱 |
| **Netty EventLoopGroup** | Netty의 이벤트 루프 그룹이 싱글톤으로 I/O 스레드를 관리 |
| **gRPC Channel** | `ManagedChannel`을 싱글톤으로 재사용하여 HTTP/2 커넥션 공유 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **EntityManagerFactory** | JPA의 `EntityManagerFactory`가 싱글톤 → 하나의 Persistence Unit에 하나의 팩토리 |
| **SessionFactory (Hibernate)** | Hibernate `SessionFactory`가 싱글톤으로 2차 캐시, 메타데이터, 커넥션풀을 관리 |
| **MyBatis SqlSessionFactory** | `SqlSessionFactory`가 싱글톤으로 매퍼 설정/캐시를 관리 |
| **Redis Connection (Lettuce/Jedis)** | Redis 커넥션 팩토리가 싱글톤으로 커넥션을 관리 |
| **MongoDB MongoClient** | `MongoClient`를 싱글톤으로 재사용 → 커넥션 풀 공유 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Logger (SLF4J/Logback)** | `LoggerFactory.getLogger(Class)` → 클래스당 하나의 Logger 인스턴스 반환하여 캐시 |
| **ThreadPoolExecutor** | 스레드 풀을 싱글톤으로 관리하여 스레드 재사용 |
| **JVM ClassLoader** | Bootstrap/Extension/Application ClassLoader가 각각 싱글톤으로 클래스를 로딩 |
| **Garbage Collector** | JVM의 GC가 단일 인스턴스로 메모리를 관리 |
| **Spring Scheduler (TaskScheduler)** | 스케줄러 인스턴스가 싱글톤으로 등록되어 `@Scheduled` 작업을 관리 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Cache Manager (Caffeine, EhCache)** | 캐시 매니저가 싱글톤으로 여러 캐시 영역을 관리 |
| **Object Pool 패턴** | 풀 매니저 자체가 싱글톤으로 풀링된 객체들을 관리 |
| **Registry 패턴** | 객체 레지스트리가 싱글톤으로 등록된 객체들의 조회 테이블 역할 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Producer** | `KafkaProducer`를 싱글톤으로 재사용 → 내부 버퍼/배치 전송 최적화 |
| **RabbitMQ ConnectionFactory** | `ConnectionFactory`를 싱글톤으로 관리하여 커넥션 재사용 |
| **Elasticsearch RestHighLevelClient** | ES 클라이언트를 싱글톤으로 사용하여 HTTP 커넥션 풀 공유 |
