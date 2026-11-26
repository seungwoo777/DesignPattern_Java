# 전략 패턴 (Strategy Pattern)

## 패턴 개요
알고리즘을 인터페이스로 캡슐화하여 런타임에 교체 가능하게 하는 패턴. 조건문 없이 행동을 동적으로 변경할 수 있다.

## 특징
- 알고리즘(전략)을 인터페이스로 정의하고 각 전략을 구현 클래스로 분리
- Context 객체가 전략 객체를 합성(Composition)하여 사용
- 런타임에 전략 교체 가능 (setStrategy)
- if-else / switch 문을 제거하고 다형성으로 대체

## 장점
- 개방-폐쇄 원칙(OCP) → 새 전략 추가 용이
- 알고리즘 교체가 유연함
- 테스트 용이 (전략별 단위 테스트)

## 단점
- 전략 클래스 수 증가
- 클라이언트가 전략의 차이를 알아야 선택 가능

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Security AuthenticationProvider** | 인증 방식(DaoAuth/JWT/OAuth2/LDAP/SAML)을 전략으로 교체. `ProviderManager`가 Context |
| **ResourceLoader / ResourcePatternResolver** | 리소스 타입(classpath:/file:/http:/s3:)에 따라 다른 로딩 전략을 선택 |
| **PlatformTransactionManager** | JDBC/JPA/JTA/R2DBC 등 트랜잭션 전략을 환경에 따라 교체 |
| **ViewResolver** | InternalResourceViewResolver, ThymeleafViewResolver, JsonViewResolver 등 뷰 렌더링 전략 교체 |
| **HandlerMethodArgumentResolver** | 컨트롤러 파라미터 타입에 따라 다른 바인딩 전략 적용 (@RequestBody, @PathVariable, @ModelAttribute) |
| **Spring Cache CacheManager** | ConcurrentMapCacheManager, RedisCacheManager, EhCacheCacheManager 등 캐시 전략 교체 |
| **Spring Retry BackOffPolicy** | FixedBackOffPolicy, ExponentialBackOffPolicy, UniformRandomBackOffPolicy 등 재시도 간격 전략 |
| **Spring Cloud LoadBalancer** | RoundRobin, Random, WeightedResponseTime 등 로드밸런싱 전략 교체 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **Collections.sort(Comparator)** | 정렬 전략을 Comparator 인터페이스로 주입 → 전략 패턴의 가장 대표적인 JDK 예제 |
| **ThreadPoolExecutor RejectedExecutionHandler** | AbortPolicy / CallerRunsPolicy / DiscardPolicy / DiscardOldestPolicy → 스레드풀 거부 전략 |
| **java.util.zip (Deflater/GZIPOutputStream)** | GZIP, Deflate, ZIP 등 압축 알고리즘 전략 교체 |
| **java.text.Collator** | Locale에 따라 문자열 비교 전략이 달라짐 (한국어/영어/중국어 정렬 규칙) |
| **javax.crypto.Cipher** | AES, RSA, DES, ChaCha20 등 암호화 알고리즘 전략을 `Cipher.getInstance("AES")`로 교체 |
| **java.util.concurrent.locks.Lock** | ReentrantLock, ReadWriteLock, StampedLock 등 잠금 전략 교체 |
| **java.security.MessageDigest** | SHA-256, SHA-512, MD5 등 해시 알고리즘 전략을 `MessageDigest.getInstance()`로 교체 |
| **java.nio.charset.Charset** | UTF-8, EUC-KR, ISO-8859-1 등 문자 인코딩 전략 교체 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **로드밸런싱 알고리즘** | Round Robin, Least Connections, Weighted, IP Hash, Consistent Hashing 등 분배 전략 교체 |
| **라우팅 프로토콜** | OSPF(최단경로), RIP(홉수), BGP(정책기반) 등 라우팅 전략 교체 |
| **HTTP Content Negotiation** | Accept 헤더에 따라 JSON/XML/HTML/MessagePack 등 직렬화 전략 교체 |
| **TLS Cipher Suite** | 클라이언트-서버 협상으로 암호화 알고리즘 전략 선택 (ECDHE-RSA-AES256-GCM-SHA384 등) |
| **DNS 레코드 기반 라우팅** | Latency-based, Geolocation, Weighted, Failover 등 Route 53 라우팅 전략 |
| **TCP 혼잡 제어 알고리즘** | Reno, CUBIC, BBR 등 TCP 혼잡 제어 전략을 커널 레벨에서 교체 가능 |
| **HTTP 캐시 전략** | Cache-Control, ETag, Last-Modified 등 캐시 무효화 전략 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **쿼리 실행 계획 (Query Plan)** | Optimizer가 Seq Scan / Index Scan / Hash Join / Nested Loop 등 실행 전략을 비용 기반으로 선택 |
| **인덱스 전략** | B-Tree, Hash, GIN, GiST, BRIN 등 데이터 특성에 따른 인덱스 전략 선택 |
| **파티셔닝 전략** | Range, Hash, List, Composite 파티셔닝 전략 교체 |
| **Replication 전략** | 동기/비동기/반동기 복제 전략. Statement-based / Row-based / Mixed 복제 모드 |
| **캐시 교체 전략** | LRU, LFU, ARC, CLOCK 등 버퍼 풀의 페이지 교체 전략 |
| **Sharding 전략** | 해시 샤딩, 레인지 샤딩, 디렉토리 샤딩 등 데이터 분산 전략 교체 |
| **JPA Fetch 전략** | FetchType.LAZY vs EAGER, Join Fetch, Batch Fetch 등 로딩 전략 교체 |
| **Connection Pool 전략** | HikariCP, DBCP2, Tomcat Pool 등 커넥션 풀 구현 전략 교체 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **CPU 스케줄링 알고리즘** | FCFS, SJF, Round Robin, Priority, Multilevel Queue 등 프로세스 스케줄링 전략 교체 |
| **페이지 교체 알고리즘** | LRU, FIFO, Clock, LFU 등 가상 메모리 페이지 교체 전략 |
| **디스크 스케줄링** | FCFS, SSTF, SCAN, C-SCAN, LOOK 등 디스크 I/O 스케줄링 전략 |
| **메모리 할당 전략** | First Fit, Best Fit, Worst Fit, Buddy System 등 메모리 할당 알고리즘 교체 |
| **GC 알고리즘** | Serial, Parallel, CMS, G1, ZGC, Shenandoah 등 JVM 가비지 컬렉션 전략 교체 |
| **파일시스템** | ext4, XFS, Btrfs, ZFS, NTFS 등 파일시스템 전략 교체 → 같은 VFS 인터페이스 |
| **컨테이너 런타임** | Docker(containerd), CRI-O, Kata Containers 등 OCI 호환 런타임 전략 교체 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **정렬 알고리즘** | QuickSort, MergeSort, TimSort, HeapSort 등 데이터 특성에 따른 정렬 전략 선택 |
| **검색 알고리즘** | Linear Search, Binary Search, Hash Table, B-Tree Search 등 검색 전략 교체 |
| **그래프 탐색** | BFS, DFS, Dijkstra, A*, Bellman-Ford 등 그래프 탐색 전략 교체 |
| **해시 함수 전략** | Division, Multiplication, Universal Hashing, Cuckoo Hashing 등 해시 충돌 전략 |
| **직렬화 전략** | JSON, Protocol Buffers, Avro, MessagePack, Thrift 등 직렬화/역직렬화 전략 교체 |
| **상태 패턴과의 차이** | 전략: 클라이언트가 명시적으로 알고리즘 선택. 상태: 내부 상태에 따라 자동으로 행동 변경 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Partitioner** | DefaultPartitioner, RoundRobinPartitioner, Custom Partitioner 등 파티션 분배 전략 교체 |
| **메시지 직렬화 전략** | StringSerializer, JsonSerializer, AvroSerializer, ProtobufSerializer 등 교체 |
| **RabbitMQ Exchange Type** | Direct, Fanout, Topic, Headers 등 메시지 라우팅 전략 교체 |
| **Dead Letter 처리 전략** | 재시도, DLQ 이동, 로깅 후 폐기, 수동 처리 대기 등 실패 메시지 처리 전략 |
| **메시지 압축 전략** | Kafka의 none, gzip, snappy, lz4, zstd 등 메시지 압축 전략 교체 |
| **Consumer 오프셋 커밋 전략** | Auto Commit, Manual Commit, Sync/Async Commit 등 오프셋 관리 전략 교체 |
