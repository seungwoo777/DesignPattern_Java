# 빌더 패턴 (Builder Pattern)

## 패턴 개요
복잡한 객체의 생성 과정을 단계별로 분리하여, 동일한 생성 절차로 다양한 표현을 만들 수 있게 하는 패턴

## 특징
- 복잡한 객체를 단계적으로 생성 (Fluent API / 메서드 체이닝)
- 불변 객체(Immutable Object) 생성에 적합
- 생성자 파라미터가 많을 때 텔레스코핑 생성자(Telescoping Constructor) 문제 해결
- 필수/선택 파라미터를 명확하게 구분 가능
- GoF Builder (Director + Builder) vs Simple Builder (Effective Java 스타일) 구분

## 장점
- 가독성 높은 객체 생성 코드
- 불변 객체 생성 용이
- 유효성 검증을 build() 시점에 집중 가능
- 같은 빌드 과정으로 다른 표현 생성

## 단점
- Builder 클래스를 별도로 작성해야 하는 코드량 증가
- 간단한 객체에는 과도한 설계

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **ResponseEntity** | `ResponseEntity.ok().header("X-Custom", "val").body(data)` → HTTP 응답을 단계적으로 조합 |
| **UriComponentsBuilder** | `UriComponentsBuilder.fromPath("/api").queryParam("page", 1).build()` → URI를 단계적으로 조합 |
| **MockMvcRequestBuilders** | `get("/api").param("id","1").accept(MediaType.APPLICATION_JSON)` → 테스트 요청 빌더 |
| **BeanDefinitionBuilder** | Spring Bean 정의를 프로그래밍 방식으로 단계적 생성 |
| **WebClient** | `WebClient.builder().baseUrl("...").defaultHeader("...").build()` → 리액티브 HTTP 클라이언트 |
| **Spring Security** | `HttpSecurity` 설정이 빌더 체이닝: `.csrf().disable().authorizeRequests().antMatchers(...)` |
| **Spring Batch** | `JobBuilder`, `StepBuilder`, `FlowBuilder` → 배치 Job을 단계적으로 조합 |
| **QueryDSL** | `queryFactory.selectFrom(entity).where(...).orderBy(...).fetch()` → 쿼리를 빌더로 조합 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **StringBuilder / StringBuffer** | `new StringBuilder().append("hello").append(" ").append("world").toString()` → 문자열 빌더 |
| **Stream.Builder** | `Stream.builder().add(1).add(2).add(3).build()` → 스트림 빌더 |
| **ProcessBuilder** | `new ProcessBuilder("ls", "-la").directory(new File("/")).start()` → 프로세스 빌더 |
| **Locale.Builder** | `new Locale.Builder().setLanguage("ko").setRegion("KR").build()` |
| **Calendar.Builder** | Java 8 이전 날짜 객체 빌더 |
| **HttpRequest (Java 11+)** | `HttpRequest.newBuilder().uri(URI.create("...")).GET().build()` |
| **java.nio.ByteBuffer** | `ByteBuffer.allocate(1024).put(data).flip()` → 버퍼 단계적 구성 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **OkHttpClient.Builder** | `.connectTimeout(10, SECONDS).addInterceptor(...).build()` → HTTP 클라이언트를 단계적 구성 |
| **Retrofit.Builder** | `.baseUrl("...").addConverterFactory(GsonConverterFactory.create()).build()` |
| **Netty ServerBootstrap** | `.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(...)` |
| **gRPC ManagedChannelBuilder** | `.forAddress("localhost", 8080).usePlaintext().build()` → gRPC 채널 빌더 |
| **Apache HttpClient** | `HttpClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(20).build()` |
| **HTTP 요청/응답 헤더** | HTTP 메시지 자체가 헤더를 단계적으로 추가하여 구성하는 빌더 개념 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JPA Criteria API** | `cb.createQuery(Entity.class)` → Predicate를 단계적으로 조합하여 쿼리 빌드 |
| **QueryDSL BooleanBuilder** | `.and(entity.name.eq("test")).or(entity.age.gt(20))` → 동적 쿼리 조건 빌더 |
| **MyBatis SQL Builder** | `new SQL().SELECT("*").FROM("users").WHERE("id = #{id}")` |
| **JOOQ DSL** | `dsl.select(USERS.NAME).from(USERS).where(USERS.AGE.gt(20)).fetch()` |
| **Elasticsearch QueryBuilder** | `QueryBuilders.boolQuery().must(matchQuery("field","value"))` → 검색 쿼리 빌더 |
| **MongoDB Aggregation** | `Aggregation.newAggregation(match(...), group(...), sort(...))` |
| **Flyway/Liquibase Migration** | 마이그레이션 스크립트를 단계적으로 구성 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Docker Compose YAML** | 서비스 정의를 단계적으로 조합하는 선언적 빌더 |
| **Gradle Build Script** | `dependencies { implementation '...' }` → 빌드 설정을 DSL 빌더로 구성 |
| **JVM 옵션** | `-Xmx512m -Xms256m -XX:+UseG1GC` → JVM 설정을 단계적으로 조합 |
| **Lombok @Builder** | 컴파일 타임에 Builder 코드를 자동 생성해주는 어노테이션 프로세서 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Immutable Collection 빌더** | `ImmutableList.builder().add(1).add(2).build()` (Guava) |
| **Protobuf Message Builder** | `Person.newBuilder().setName("test").setAge(20).build()` → 직렬화 객체 빌더 |
| **JSON 빌더 (Jackson/Gson)** | `ObjectMapper`의 `.configure(...)` 체이닝으로 직렬화 설정 조합 |
| **Graph Builder (Guava)** | `GraphBuilder.directed().allowsSelfLoops(true).build()` → 그래프 자료구조 빌더 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka ProducerRecord Builder** | 토픽/키/값/헤더를 단계적으로 조합하여 메시지 생성 |
| **Spring AMQP MessageBuilder** | `MessageBuilder.withBody(data).setHeader("key","value").build()` |
| **AWS SDK v2 Builder** | `S3Client.builder().region(Region.AP_NORTHEAST_2).build()` → AWS 서비스 클라이언트 빌더 |
