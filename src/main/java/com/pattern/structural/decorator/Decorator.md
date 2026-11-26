# 데코레이터 패턴 (Decorator Pattern)

## 패턴 개요
객체에 동적으로 새로운 기능을 추가할 수 있게 해주는 패턴. 상속 대신 조합(Composition)을 사용하여 런타임에 유연하게 기능을 확장한다.

## 특징
- 기존 객체를 감싸는(Wrapping) 방식으로 기능 추가
- 여러 데코레이터를 중첩하여 기능 조합 가능
- 원본 객체와 데코레이터가 동일한 인터페이스 구현
- 프록시 패턴과 구조는 유사하나 의도가 다름 (기능 추가 vs 접근 제어)

## 장점
- 단일 책임 원칙(SRP) → 각 데코레이터가 하나의 기능 담당
- 개방-폐쇄 원칙(OCP) → 기존 코드 수정 없이 기능 확장
- 런타임에 유연한 기능 조합

## 단점
- 데코레이터 중첩이 많아지면 디버깅 어려움
- 초기 생성 코드가 복잡해질 수 있음
- 특정 데코레이터만 제거하기 어려움

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring AOP** | `@Transactional`, `@Cacheable`, `@Async`, `@Retryable` 등 어노테이션이 프록시/데코레이터로 부가 기능을 추가. 핵심은 데코레이터 체이닝 |
| **HttpServletRequestWrapper / ResponseWrapper** | Servlet 요청/응답에 로깅, 인증 정보, 커스텀 헤더 등을 데코레이터로 추가 |
| **TransactionAwareCacheDecorator** | Cache에 트랜잭션 인식 기능을 데코레이터로 추가 |
| **BeanDefinitionDecorator** | Bean 정의에 AOP, 스코프, 트랜잭션 등을 데코레이터로 추가 |
| **Spring Security Filter** | SecurityFilterChain이 각 Filter를 데코레이터처럼 중첩하여 보안 기능 추가 |
| **Spring Cloud Gateway Filter** | Pre/Post GatewayFilter가 요청에 인증/로깅/Rate Limiting을 데코레이터로 추가 |
| **WebClient ExchangeFilterFunction** | HTTP 요청에 인증 헤더, 로깅, 메트릭 수집을 필터(데코레이터)로 추가 |
| **Spring Retry** | `@Retryable`이 서비스 메서드에 재시도 로직을 데코레이터로 추가 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **Java I/O 스트림** | `new BufferedReader(new InputStreamReader(new FileInputStream("file")))` → 가장 대표적인 데코레이터 체인 |
| **BufferedInputStream/OutputStream** | 기본 스트림에 버퍼링 기능을 데코레이터로 추가 |
| **DataInputStream/DataOutputStream** | 기본 스트림에 프리미티브 타입 읽기/쓰기 기능을 추가 |
| **GZIPInputStream/GZIPOutputStream** | 스트림에 GZIP 압축/해제 기능을 데코레이터로 추가 |
| **CipherInputStream/CipherOutputStream** | 스트림에 암/복호화 기능을 데코레이터로 추가 |
| **Collections.unmodifiableList()** | 기존 List에 불변(읽기 전용) 기능을 데코레이터로 추가 |
| **Collections.synchronizedList()** | 기존 List에 동기화 기능을 데코레이터로 추가 |
| **Collections.checkedList()** | 기존 List에 타입 체크 기능을 데코레이터로 추가 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **SSL/TLS 래핑** | 일반 Socket을 `SSLSocket`으로 감싸서 암호화 기능 추가 → 네트워크 데코레이터 |
| **HTTP Interceptor / Middleware** | OkHttp Interceptor, Express.js middleware → 요청에 인증/로깅/압축을 데코레이터로 추가 |
| **프록시 서버 체인** | Forward Proxy → Reverse Proxy → CDN → Origin 각 단계가 기능을 추가하는 데코레이터 |
| **gRPC Interceptor** | ClientInterceptor/ServerInterceptor가 RPC 호출에 로깅/인증/메트릭을 데코레이터로 추가 |
| **TCP 프로토콜 스택** | Ethernet → IP → TCP → TLS → HTTP 각 계층이 하위 계층에 기능을 추가 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **Connection Pool 래핑** | HikariCP의 `HikariProxyConnection`이 실제 Connection에 풀 관리/통계 기능을 데코레이터로 추가 |
| **P6Spy** | JDBC Connection/Statement를 래핑하여 SQL 로깅 기능을 데코레이터로 추가 |
| **MyBatis Plugin (Interceptor)** | Executor, StatementHandler, ParameterHandler를 래핑하여 로깅/페이징 기능 추가 |
| **Spring @Transactional** | DataSource를 `TransactionAwareDataSourceProxy`로 래핑하여 트랜잭션 기능 추가 |
| **쿼리 캐시 래핑** | 쿼리 결과를 캐시하는 데코레이터를 Repository에 추가 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **파이프라인 (Unix Pipe)** | `cat file | grep "error" | sort | uniq` → 각 명령어가 입력 스트림에 기능을 추가하는 데코레이터 |
| **Linux iptables 체인** | INPUT → FORWARD → OUTPUT 각 체인이 패킷에 필터/NAT/로깅 기능을 추가 |
| **Middleware 스택** | CORS → Authentication → Authorization → Rate Limiting → Handler 미들웨어 체인 |
| **Docker 이미지 레이어** | 베이스 이미지 위에 레이어를 쌓아 기능을 추가하는 데코레이터 구조 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Guava ForwardingList/Set/Map** | 기존 컬렉션에 로깅/검증/통계 기능을 데코레이터로 추가 |
| **스트림 연산 체이닝** | `stream().filter().map().sorted().collect()` → 각 연산이 이전 스트림에 기능을 추가 |
| **Reactive 연산자** | `Flux.just().map().filter().retry().onErrorResume()` → 리액티브 스트림 데코레이터 체인 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Producer Interceptor** | 메시지 전송에 직렬화/압축/헤더추가를 데코레이터로 추가 |
| **Spring AMQP MessagePostProcessor** | RabbitMQ 메시지에 헤더/우선순위/TTL을 데코레이터로 추가 |
| **Spring Cloud Sleuth** | 모든 HTTP/메시지 요청에 트레이싱 ID를 데코레이터(Interceptor)로 자동 추가 |
