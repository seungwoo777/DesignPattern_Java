# 플라이웨이트 패턴 (Flyweight Pattern)

## 패턴 개요
동일한 데이터를 가진 객체를 공유하여 메모리 사용을 최소화하는 패턴. 대량의 유사 객체가 필요할 때 캐시를 통해 중복 생성을 방지한다.

## 특징
- 내재적 상태(Intrinsic): 공유 가능한 불변 데이터 → 플라이웨이트 객체에 저장
- 외재적 상태(Extrinsic): 컨텍스트에 따라 달라지는 데이터 → 메서드 파라미터로 전달
- 팩토리를 통한 캐시 관리 (동일 객체 재사용)
- 불변 객체(Immutable Object)와 함께 사용하면 효과적

## 장점
- 대량의 유사 객체 생성 시 메모리 대폭 절감
- 객체 생성 비용 절감

## 단점
- 코드 복잡도 증가 (내재적/외재적 상태 분리)
- CPU 연산 증가 (캐시 조회 오버헤드)
- 멀티스레드 환경에서 동기화 필요

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Bean (Singleton Scope)** | 싱글톤 빈이 공유 객체로서 플라이웨이트 역할. 상태 없는(Stateless) 빈을 모든 요청이 공유 |
| **Spring Cache (@Cacheable)** | 동일 파라미터에 대한 메서드 결과를 캐싱하여 공유. 중복 연산/조회 방지 |
| **ObjectMapper (Jackson)** | 하나의 ObjectMapper 인스턴스를 공유하여 직렬화/역직렬화 처리 (Thread-safe) |
| **Spring Expression Parser** | SpEL 파서가 파싱된 Expression 객체를 캐싱하여 재사용 |
| **Spring MVC MediaType** | `MediaType.APPLICATION_JSON` 등 자주 사용되는 미디어 타입을 상수로 공유 |
| **HttpStatus** | `HttpStatus.OK`, `HttpStatus.NOT_FOUND` 등 HTTP 상태 코드를 enum으로 공유 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **String Pool (String Intern)** | String 리터럴이 String Pool에서 공유. `"hello" == "hello"`가 true인 이유. `String.intern()` |
| **Integer Cache (-128 ~ 127)** | `Integer.valueOf(127) == Integer.valueOf(127)` → true. 오토박싱 시 캐시된 Integer 공유 |
| **Boolean.valueOf()** | `Boolean.TRUE`, `Boolean.FALSE` 두 개의 인스턴스만 공유 |
| **Byte/Short/Long Cache** | -128~127 범위의 래퍼 객체를 캐싱하여 공유 |
| **Character Cache (0 ~ 127)** | ASCII 범위의 Character 객체를 캐싱 |
| **Enum** | 각 enum 상수가 JVM에서 하나만 존재하는 플라이웨이트 |
| **Class 객체** | 동일 클래스의 `Class<?>` 객체는 JVM에서 하나만 존재하여 공유 |
| **Charset.forName()** | `StandardCharsets.UTF_8` 등 자주 사용되는 Charset을 캐싱 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **Connection Pool** | HikariCP, DBCP, Tomcat Pool → 커넥션 객체를 풀에서 공유/재사용하여 생성 비용 절감 |
| **Thread Pool** | `ThreadPoolExecutor`에서 스레드를 풀에서 공유/재사용 |
| **HTTP/2 Multiplexing** | 하나의 TCP 커넥션을 여러 HTTP 요청이 공유 (커넥션 플라이웨이트) |
| **DNS 캐시** | DNS 조회 결과를 캐싱하여 동일 도메인 요청이 캐시된 IP를 공유 |
| **TLS Session Resumption** | TLS 핸드셰이크 세션을 캐싱하여 재연결 시 공유 |
| **Keep-Alive 커넥션** | HTTP Keep-Alive로 TCP 커넥션을 여러 요청이 재사용 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **PreparedStatement 캐시** | 동일 SQL의 PreparedStatement를 캐싱하여 파싱/컴파일 비용 절감 |
| **Hibernate 1차 캐시** | 같은 영속성 컨텍스트 내에서 동일 엔티티를 캐싱하여 공유 (Identity Map) |
| **Hibernate 2차 캐시** | SessionFactory 레벨에서 엔티티를 캐싱하여 여러 Session이 공유 |
| **Query Plan Cache** | Hibernate가 JPQL/HQL 쿼리 플랜을 캐싱하여 재사용 |
| **Oracle Shared Pool** | Oracle DB의 Shared Pool이 SQL 파싱 결과, 실행 계획을 여러 세션이 공유 |
| **Redis** | 자주 사용되는 데이터를 메모리에 캐싱하여 여러 서비스/요청이 공유 |
| **Buffer Pool (InnoDB)** | MySQL InnoDB의 버퍼 풀이 디스크 페이지를 메모리에 캐싱하여 공유 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Copy-on-Write (COW)** | fork() 시 부모/자식 프로세스가 메모리 페이지를 공유. 수정 시에만 복사 |
| **Shared Library (.so/.dll)** | 여러 프로세스가 동일 라이브러리의 코드 세그먼트를 메모리에서 공유 |
| **Page Cache** | OS가 디스크 블록을 메모리에 캐싱하여 여러 프로세스가 공유 |
| **inode** | 파일시스템에서 같은 파일의 메타데이터(inode)를 공유. 하드링크가 대표적 |
| **Memory-Mapped Files** | mmap으로 파일을 메모리에 매핑하여 여러 프로세스가 공유 |
| **Docker Image Layer** | 베이스 이미지 레이어를 여러 컨테이너가 공유하여 디스크/메모리 절약 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Hash Consing** | 동일 값을 가진 노드를 해시 테이블에서 공유하는 함수형 프로그래밍 기법 |
| **Interning** | 중복 문자열/객체를 하나만 유지하고 참조를 공유 (String.intern()의 일반화) |
| **Persistent Data Structure** | 함수형 자료구조에서 공유 가능한 노드를 재사용 (Structural Sharing) |
| **Font Glyph Cache** | 폰트 렌더링 시 동일 글리프를 캐싱하여 공유 |
| **Bitmap / Bitset** | 비트 단위로 상태를 저장하여 메모리를 극한으로 절약 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Producer 배치** | 같은 파티션으로 가는 메시지를 배치로 묶어 네트워크 리소스 공유 |
| **Object Pool (Apache Commons Pool)** | 비용이 큰 객체(커넥션, 세션)를 풀에서 공유/재사용 |
| **Spring Cloud Config 캐시** | 설정 데이터를 캐싱하여 여러 서비스 인스턴스가 공유 |
| **Caffeine Cache** | 고성능 로컬 캐시로 계산 결과/조회 결과를 공유 |
