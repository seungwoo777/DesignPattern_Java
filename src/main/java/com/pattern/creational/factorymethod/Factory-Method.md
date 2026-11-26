# 팩토리 메서드 패턴 (Factory Method Pattern)

## 패턴 개요
객체 생성을 서브클래스에 위임하여, 어떤 클래스의 인스턴스를 생성할지를 하위 클래스가 결정하도록 하는 패턴

## 특징
- 객체 생성 로직을 하위 클래스에 위임
- 클라이언트는 구체 클래스를 알 필요 없이 팩토리를 통해 객체 생성
- 새로운 타입 추가 시 기존 코드 수정 없이 팩토리만 추가 (OCP 준수)
- 정적 팩토리 메서드(Static Factory Method)와 구분 필요: 정적 팩토리는 GoF 패턴이 아닌 Effective Java 관용구

## 장점
- 객체 생성과 사용의 분리 (느슨한 결합)
- 개방-폐쇄 원칙(OCP) 준수
- 단일 책임 원칙(SRP) 준수

## 단점
- 클래스 수 증가 (팩토리 + 제품 클래스)
- 간단한 객체 생성에는 과도한 구조

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **BeanFactory / ApplicationContext** | Bean 이름이나 타입으로 `getBean()` 호출 시 적절한 객체를 생성/반환하는 핵심 팩토리 |
| **FactoryBean\<T\>** | `FactoryBean` 인터페이스를 구현하여 복잡한 Bean 생성 로직을 캡슐화. `ProxyFactoryBean`, `JndiObjectFactoryBean` 등 |
| **Spring Data Repository** | `JpaRepositoryFactory`가 인터페이스만 보고 구현체(SimpleJpaRepository)를 동적으로 생성 |
| **HandlerMapping** | URL 패턴에 따라 적절한 Handler(Controller)를 찾아 반환하는 팩토리 역할 |
| **ViewResolver** | 뷰 이름에 따라 적절한 View 객체(ThymeleafView, JstlView 등)를 생성 |
| **Spring Cloud Gateway RouteLocator** | 라우팅 규칙에 따라 Route 객체를 생성하는 팩토리 |
| **RestTemplateBuilder** | `build()` 호출 시 설정에 맞는 RestTemplate 인스턴스를 생성하는 팩토리 역할 |
| **ObjectProvider\<T\>** | Lazy하게 Bean을 조회/생성하는 팩토리 프로바이더 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **Calendar.getInstance()** | Locale에 따라 `GregorianCalendar`, `JapaneseImperialCalendar` 등 다른 구현체 반환 |
| **NumberFormat.getInstance()** | Locale에 따라 다른 NumberFormat 구현체 반환 |
| **Charset.forName()** | 이름에 따라 `UTF-8`, `EUC-KR` 등 다른 Charset 구현체 반환 |
| **URLStreamHandlerFactory** | URL 프로토콜(http, ftp, file)에 따라 다른 StreamHandler 생성 |
| **Collection.iterator()** | ArrayList, LinkedList, HashSet 등 각 컬렉션이 자신에 맞는 Iterator 구현체를 생성하여 반환 |
| **EnumSet.of()** | 원소 개수에 따라 `RegularEnumSet` 또는 `JumboEnumSet`을 생성 |
| **Optional.of() / Optional.empty()** | 정적 팩토리 메서드로 Optional 인스턴스 생성 |
| **Executors.newFixedThreadPool()** | 타입별로 다른 ThreadPoolExecutor 구성을 반환 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **SocketFactory / SSLSocketFactory** | 프로토콜에 따라 일반 Socket 또는 SSL Socket을 생성하는 팩토리 |
| **ConnectionFactory (JMS/AMQP)** | 프로토콜/설정에 따라 다른 Connection 구현체를 생성 |
| **ChannelFactory (Netty)** | 타입에 따라 `NioSocketChannel`, `EpollSocketChannel` 등 생성 |
| **WebSocket HandshakeHandler** | WebSocket 핸드셰이크 시 적절한 WebSocketSession을 생성 |
| **Protocol Buffer** | `.parseFrom()` 메서드가 바이트 스트림으로부터 적절한 메시지 객체를 생성 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **DriverManager.getConnection()** | URL 스킴(jdbc:mysql, jdbc:postgresql 등)에 따라 다른 Connection 구현체 반환 |
| **DataSource** | JNDI DataSource, HikariDataSource, DriverManagerDataSource 등 설정에 따라 다른 DataSource 생성 |
| **Hibernate Dialect** | DB 종류에 따라 `MySQLDialect`, `PostgreSQLDialect`, `OracleDialect` 등 자동 선택 |
| **Spring Data Repository** | 인터페이스 선언만으로 JPA/MongoDB/Redis 등 저장소별 구현체를 팩토리가 자동 생성 |
| **MyBatis SqlSessionFactory** | 설정에 따라 다른 SqlSession 구현체를 생성하여 반환 |
| **JOOQ DSLContext** | DB 방언에 따라 적절한 SQL 생성기를 팩토리로 반환 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **LoggerFactory.getLogger()** | SLF4J가 바인딩된 로깅 프레임워크(Logback/Log4j2)에 따라 다른 Logger 구현체 반환 |
| **Files.newInputStream() / newOutputStream()** | OS에 따라 다른 I/O 스트림 구현체를 생성 |
| **ProcessBuilder** | OS에 따라 다른 Process 구현체(UnixProcess, WindowsProcess)를 생성 |
| **FileSystem.getDefault()** | OS에 따라 `UnixFileSystem`, `WindowsFileSystem` 등 반환 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Collections.unmodifiableList()** | 원본 List를 감싸는 UnmodifiableList를 팩토리 메서드로 생성 |
| **List.of() / Map.of() (Java 9+)** | 원소 개수에 따라 최적화된 불변 컬렉션 구현체를 팩토리로 생성 |
| **Arrays.asList()** | 배열을 감싸는 고정 크기 List를 팩토리로 생성 |
| **Guava Iterables/Iterators** | 다양한 반복자 구현체를 팩토리 메서드로 생성 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Serializer/Deserializer** | 데이터 타입에 따라 `StringSerializer`, `JsonSerializer`, `AvroSerializer` 등 생성 |
| **Spring Cloud Stream Binder** | 메시지 브로커(Kafka/RabbitMQ)에 따라 다른 Binder 구현체를 팩토리로 생성 |
| **Jackson ObjectMapper TypeFactory** | 타입 정보에 따라 적절한 Deserializer를 팩토리로 생성 |
