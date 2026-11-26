# 어댑터 패턴 (Adapter Pattern)

## 패턴 개요
호환되지 않는 인터페이스를 가진 클래스를 함께 동작할 수 있도록 변환해주는 패턴. 기존 코드를 수정하지 않고 새로운 인터페이스에 맞춰 사용할 수 있게 한다.

## 특징
- 클래스 어댑터 (상속 기반) vs 객체 어댑터 (합성 기반)
- 레거시 시스템과 새 시스템 간의 브릿지 역할
- Wrapper 패턴이라고도 불림
- 양방향 어댑터(Two-Way Adapter)도 가능

## 장점
- 기존 코드 수정 없이 새로운 인터페이스 적용 (OCP 준수)
- 단일 책임 원칙(SRP) → 변환 로직을 분리
- 레거시 코드 재활용 극대화

## 단점
- 어댑터 클래스가 늘어나면 코드 복잡도 증가
- 과도한 사용 시 코드 흐름 파악 어려움

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **HandlerAdapter** | DispatcherServlet이 다양한 Handler 타입(@Controller, HttpRequestHandler, Servlet)을 통일된 방식으로 처리할 수 있도록 어댑터로 변환 |
| **JpaVendorAdapter** | Hibernate/EclipseLink 등 JPA 구현체를 Spring JPA 인터페이스에 적응. `HibernateJpaVendorAdapter` 등 |
| **MessageConverterAdapter** | `HttpMessageConverter`가 HTTP 요청/응답 본문을 Java 객체로 변환하는 어댑터 역할 |
| **Spring AOP AdvisorAdapter** | 다양한 Advice 타입(MethodBeforeAdvice, AfterReturningAdvice)을 AOP Alliance의 MethodInterceptor로 적응 |
| **WebMvcConfigurerAdapter** | (레거시) WebMvcConfigurer 인터페이스의 기본 구현을 제공하는 어댑터 |
| **Spring Integration** | 외부 시스템(FTP, JMS, HTTP 등)을 Spring Messaging 채널에 적응시키는 Inbound/Outbound Adapter |
| **Spring Cloud OpenFeign** | REST API를 Java 인터페이스로 선언하면 프록시가 HTTP 호출로 적응 변환 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **InputStreamReader / OutputStreamWriter** | 바이트 스트림(InputStream)을 문자 스트림(Reader)으로 적응. 인코딩 변환 포함 |
| **Arrays.asList()** | 배열을 List 인터페이스에 적응시키는 어댑터. 고정 크기 List 반환 |
| **Collections.enumeration()** | Iterator를 Enumeration 인터페이스에 적응 (레거시 API 호환) |
| **Collections.list()** | Enumeration을 ArrayList로 변환하는 어댑터 |
| **Runnable → Callable 어댑터** | `Executors.callable(Runnable)` → Runnable을 Callable 인터페이스에 적응 |
| **java.io 스트림 체인** | FileInputStream → BufferedInputStream → DataInputStream → 각 단계가 인터페이스 적응 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **프로토콜 변환 게이트웨이** | REST ↔ gRPC, REST ↔ SOAP, HTTP ↔ WebSocket 프로토콜 간 변환 어댑터 |
| **API Gateway 변환** | 외부 API 형식을 내부 마이크로서비스 형식으로 변환 |
| **Proxy Protocol** | HAProxy/Nginx에서 클라이언트 IP를 프록시 프로토콜로 변환하여 전달 |
| **SSL Termination** | HTTPS 요청을 HTTP로 변환하여 내부 서비스에 전달하는 TLS 어댑터 |
| **DNS Adapter** | 서비스 디스커버리(Eureka/Consul)를 DNS 인터페이스로 적응 (CoreDNS) |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JDBC-ODBC Bridge** | ODBC 드라이버를 JDBC 인터페이스에 적응시키는 브릿지 어댑터 |
| **JPA AttributeConverter** | DB 컬럼 타입과 Java 타입 간 변환 어댑터. `@Convert(converter = ...)` |
| **MyBatis TypeHandler** | DB 타입과 Java 타입 간 매핑을 처리하는 어댑터 |
| **Spring Data Custom Repository** | 커스텀 쿼리 로직을 Spring Data Repository 인터페이스에 적응 |
| **Hibernate UserType** | 비표준 DB 타입을 Hibernate가 처리할 수 있도록 적응 |
| **R2DBC Adapter** | 블로킹 JDBC 코드를 리액티브 R2DBC 인터페이스에 적응 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **디바이스 드라이버** | 하드웨어(프린터, 그래픽카드)의 고유 인터페이스를 OS 표준 인터페이스에 적응 |
| **POSIX API** | 각 Unix 변종(Linux/macOS/BSD)의 시스템 호출을 POSIX 표준 인터페이스에 적응 |
| **WSL (Windows Subsystem for Linux)** | Linux 시스템 호출을 Windows 커널 호출로 변환하는 어댑터 |
| **JNI (Java Native Interface)** | C/C++ 네이티브 코드를 Java 인터페이스에 적응 |
| **Docker** | 호스트 OS 위에서 컨테이너의 시스템 호출을 호스트 커널로 적응 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Stack → Deque 어댑터** | Java에서 Stack 대신 `ArrayDeque`를 사용하는 것은 Deque를 Stack 인터페이스처럼 사용하는 어댑터 개념 |
| **Iterator → Stream 변환** | `StreamSupport.stream(iterator.spliterator(), false)` → Iterator를 Stream에 적응 |
| **Comparator 어댑터** | `Comparator.comparing(Function)` → Function을 Comparator 인터페이스에 적응 |
| **Guava ForwardingList** | 기존 List에 추가 기능을 적응시키는 Forwarding 어댑터 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Connect Connector** | 외부 시스템(DB, S3, Elasticsearch)을 Kafka 토픽에 적응시키는 Source/Sink Connector |
| **Spring Cloud Stream** | Kafka/RabbitMQ의 고유 API를 Spring Messaging의 `MessageChannel` 인터페이스에 적응 |
| **JMS ↔ AMQP 브릿지** | JMS 메시지를 AMQP 프로토콜에 적응시키는 브릿지 어댑터 |
