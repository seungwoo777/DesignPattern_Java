# 추상 팩토리 패턴 (Abstract Factory Pattern)

## 패턴 개요
관련된 객체들의 군(family)을 일관되게 생성하기 위한 인터페이스를 제공하는 패턴. 구체적인 클래스를 지정하지 않고 관련 객체들을 세트로 생성한다.

## 특징
- 관련된 객체 군을 한 번에 일관되게 생성
- 팩토리 메서드 패턴의 확장 (팩토리의 팩토리)
- 제품 군 간의 일관성 보장 (호환되지 않는 조합 방지)
- 새로운 제품 군 추가는 쉬우나, 새로운 제품 종류 추가는 어려움

## 장점
- 제품 군의 일관성 보장
- 구체 클래스 분리 (클라이언트는 추상 인터페이스만 의존)
- 제품 군 교체 용이 (팩토리 하나만 교체)

## 단점
- 새로운 종류의 제품 추가 시 모든 팩토리 수정 필요
- 클래스 수가 많아질 수 있음

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Security 인증 체계** | 인증 방식(JWT/OAuth2/Session)에 따라 관련 객체 세트(AuthenticationProvider + Filter + TokenStore + Handler)를 일관되게 생성 |
| **PlatformTransactionManager** | DB 종류에 따라 트랜잭션 관련 객체 세트(TransactionManager + TransactionStatus + TransactionDefinition)를 일관 생성 |
| **ViewResolver 체계** | 뷰 기술(Thymeleaf/JSP/Freemarker)에 따라 ViewResolver + View + TemplateEngine 세트를 생성 |
| **Spring Data 저장소 추상화** | 저장소 종류(JPA/MongoDB/Redis)에 따라 Repository + Query + Transaction 세트를 일관 생성 |
| **Spring Cloud Config** | 설정 소스(Git/Vault/JDBC)에 따라 EnvironmentRepository + PropertySource + Watcher 세트를 생성 |
| **Spring Messaging** | 메시지 브로커(Kafka/RabbitMQ/ActiveMQ)에 따라 ConnectionFactory + Template + ListenerContainer 세트를 생성 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **javax.xml.parsers** | `DocumentBuilderFactory`, `SAXParserFactory`, `TransformerFactory` → XML 처리 객체 군을 세트로 생성 |
| **javax.xml.xpath.XPathFactory** | XPath 처리 관련 객체 군을 세트로 생성 |
| **java.awt.Toolkit** | OS에 따라 GUI 컴포넌트(Button, Checkbox, Window) 세트를 일관되게 생성 |
| **javax.swing LookAndFeel** | 테마(Metal/Nimbus/System)에 따라 UI 컴포넌트 세트를 일관 생성 |
| **JDBC Driver** | DB 벤더에 따라 Connection + Statement + ResultSet + DatabaseMetaData 세트를 일관 생성 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **클라우드 벤더별 SDK** | AWS/GCP/Azure별로 Storage + Queue + Notification + Auth 클라이언트를 세트로 생성 |
| **통신 프로토콜 세트** | gRPC: Channel + Stub + Interceptor 세트 / REST: Client + Converter + ErrorHandler 세트 |
| **Netty 채널 팩토리** | Transport(NIO/Epoll/KQueue)에 따라 ServerChannel + SocketChannel + EventLoopGroup 세트를 생성 |
| **SSL/TLS 구성** | `SSLContext`가 KeyManager + TrustManager + SecureRandom 세트를 생성 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **Hibernate Dialect 체계** | DB 벤더별로 Dialect + SQLFunction + TypeMapping + SequenceGenerator 세트를 일관 생성 |
| **JPA Persistence Provider** | Hibernate/EclipseLink/OpenJPA에 따라 EntityManager + Query + CriteriaBuilder 세트 생성 |
| **Connection Pool 체계** | HikariCP/DBCP/Tomcat Pool에 따라 DataSource + Connection + PoolMonitor 세트를 생성 |
| **검색 엔진 클라이언트** | Elasticsearch/Solr에 따라 Client + QueryBuilder + IndexManager 세트를 생성 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Java NIO FileSystem** | OS별로 FileSystem + Path + WatchService + FileStore 세트를 생성 |
| **로깅 프레임워크** | Logback/Log4j2에 따라 Logger + Appender + Encoder + Filter 세트를 일관 생성 |
| **테스트 인프라** | 테스트 환경에 따라 TestContainer + MockServer + WireMock + H2 세트를 구성 |
| **모니터링 스택** | Micrometer가 모니터링 시스템(Prometheus/Datadog/CloudWatch)에 따라 Meter + Registry + Exporter 세트를 생성 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **직렬화 프레임워크** | 포맷(JSON/XML/Protobuf/Avro)에 따라 Serializer + Deserializer + Schema 세트를 생성 |
| **Guava 컬렉션 팩토리** | 타입에 따라 ImmutableList + ImmutableSet + ImmutableMap 등 불변 컬렉션 세트를 생성 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Cloud Stream Binder** | Kafka/RabbitMQ/Kinesis에 따라 Binder + MessageChannel + ProducerFactory + ConsumerFactory 세트를 생성 |
| **Spring Integration** | 채널 타입(Direct/Queue/Publish-Subscribe)에 따라 Channel + Handler + Router 세트를 생성 |
