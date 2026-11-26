# 브릿지 패턴 (Bridge Pattern)

## 패턴 개요
추상화(Abstraction)와 구현(Implementation)을 분리하여 각각 독립적으로 변경할 수 있게 하는 패턴. 두 차원의 변화를 조합으로 처리한다.

## 특징
- 추상화와 구현의 분리 (Decouple)
- 두 개의 독립적인 계층이 조합(Composition)으로 연결
- 상속 대신 합성을 사용하여 조합 폭발(N×M → N+M) 방지
- "has-a" 관계로 유연한 확장 가능

## 장점
- 추상화와 구현을 독립적으로 확장 가능
- 런타임에 구현체 교체 가능
- 클래스 수 증가 억제

## 단점
- 초기 설계 복잡도 증가
- 단순한 경우에는 과도한 구조

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **PlatformTransactionManager** | 트랜잭션 추상화(인터페이스) + JpaTransactionManager/DataSourceTransactionManager/JtaTransactionManager(구현) 분리 |
| **Spring Logging (SLF4J)** | SLF4J API(추상화) + Logback/Log4j2/java.util.logging(구현) → 로깅 추상화의 대표적 브릿지 |
| **Spring Cache** | CacheManager(추상화) + RedisCacheManager/CaffeineCacheManager/EhCacheCacheManager(구현) 분리 |
| **Spring Resource** | Resource(추상화) + ClassPathResource/FileSystemResource/UrlResource/ByteArrayResource(구현) |
| **Spring Messaging** | MessageChannel(추상화) + DirectChannel/QueueChannel/PublishSubscribeChannel(구현) |
| **Spring Data** | Repository(추상화) + JpaRepository/MongoRepository/RedisRepository(구현) → 저장소 기술 독립 |
| **Spring Cloud LoadBalancer** | LoadBalancerClient(추상화) + RoundRobinLoadBalancer/RandomLoadBalancer(구현) |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **JDBC Driver** | `java.sql.DriverManager`(추상화) + MySQL Driver/PostgreSQL Driver/Oracle Driver(구현) → DB 독립적 코드 작성 가능 |
| **java.util.Collections** | 컬렉션 인터페이스(List/Set/Map 추상화) + ArrayList/LinkedList/HashSet/TreeMap(구현) 분리 |
| **java.util.logging.Handler** | Handler(추상화) + ConsoleHandler/FileHandler/SocketHandler(구현) |
| **java.nio.channels** | Channel(추상화) + FileChannel/SocketChannel/DatagramChannel(구현) |
| **javax.crypto.Cipher** | Cipher(추상화) + AES/RSA/DES Provider(구현) 분리 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **OSI 7계층 모델 자체** | 각 계층이 추상화(인터페이스)를 정의하고 구현(프로토콜)을 분리. 예: Transport(추상화) + TCP/UDP(구현) |
| **HTTP 추상화** | HTTP 인터페이스(추상화) + HTTP/1.1, HTTP/2, HTTP/3(QUIC)(구현) 분리 |
| **소켓 추상화** | Socket API(추상화) + TCP Socket/Unix Domain Socket/WebSocket(구현) |
| **서비스 메시 (Service Mesh)** | 서비스 통신 추상화 + Istio/Linkerd/Envoy(구현) 분리 |
| **DNS 추상화** | DNS Query 인터페이스(추상화) + UDP DNS/DoH(DNS over HTTPS)/DoT(DNS over TLS)(구현) |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JPA (Java Persistence API)** | JPA API(추상화) + Hibernate/EclipseLink/OpenJPA(구현) → 가장 대표적인 브릿지 |
| **Spring Data Repository** | Repository 인터페이스(추상화) + JPA/MongoDB/Elasticsearch/Neo4j/Cassandra(구현) |
| **Connection Pool** | DataSource(추상화) + HikariCP/DBCP2/Tomcat JDBC Pool/C3P0(구현) |
| **쿼리 추상화** | JPQL/Criteria API(추상화) + 각 DB 벤더의 네이티브 SQL(구현) |
| **트랜잭션 격리 레벨** | Isolation Level 인터페이스(추상화) + DB 벤더별 구현(MVCC/Lock-based) |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **VFS (Virtual File System)** | 파일시스템 인터페이스(추상화) + ext4/NTFS/NFS/ZFS(구현) → OS 커널의 브릿지 |
| **디바이스 드라이버 모델** | 디바이스 인터페이스(추상화) + 벤더별 드라이버(구현) |
| **하이퍼바이저** | VM 인터페이스(추상화) + KVM/VMware/Hyper-V(구현) |
| **컨테이너 런타임** | CRI(Container Runtime Interface, 추상화) + containerd/CRI-O/Docker(구현) |
| **Java NIO vs IO** | Java I/O 추상화 + Blocking IO/Non-blocking IO/Asynchronous IO(구현) |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **정렬 알고리즘** | Sortable 인터페이스(추상화) + QuickSort/MergeSort/TimSort(구현) → `Collections.sort()`가 내부 전략 선택 |
| **해시 함수** | Hashing 인터페이스(추상화) + MD5/SHA-256/MurmurHash/xxHash(구현) |
| **압축 알고리즘** | Compressor(추상화) + GZIP/LZ4/Snappy/Zstd(구현) |
| **직렬화** | Serializer(추상화) + JSON/XML/Protobuf/Avro/MessagePack(구현) |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **JMS (Java Message Service)** | JMS API(추상화) + ActiveMQ/RabbitMQ JMS Client/IBM MQ(구현) |
| **Spring Cloud Stream** | MessageChannel(추상화) + Kafka Binder/RabbitMQ Binder/Kinesis Binder(구현) |
| **Reactive Streams** | Publisher/Subscriber(추상화) + Reactor/RxJava/Akka Streams(구현) |
