# 이터레이터 패턴 (Iterator Pattern)

## 패턴 개요
컬렉션의 내부 구조를 노출하지 않고 원소를 순차적으로 접근할 수 있게 해주는 패턴

## 특징
- 컬렉션의 순회 방법을 캡슐화
- 다양한 순회 전략을 독립적으로 구현 가능
- 동일한 컬렉션에 대해 여러 이터레이터를 동시에 사용 가능
- 외부 반복(External) vs 내부 반복(Internal, Stream)

## 장점
- 단일 책임 원칙(SRP) → 순회 로직을 별도 클래스로 분리
- 컬렉션의 내부 구조에 독립적인 순회
- Lazy Evaluation 구현 가능

## 단점
- 단순한 컬렉션에는 과도한 구조
- Java Stream API로 대부분 대체 가능

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Data Page/Slice** | 페이징 결과를 `Page<T>` → `Iterator`로 순회. `page.getContent().iterator()` |
| **Spring Batch ItemReader** | `read()` 메서드가 호출될 때마다 다음 아이템을 반환하는 커서 기반 이터레이터 |
| **Spring Data Stream** | `@Query` + `Stream<T>` 반환으로 대용량 데이터를 스트림 이터레이터로 처리 |
| **ApplicationContext.getBeansOfType()** | 특정 타입의 Bean들을 Map으로 반환하여 순회 |
| **Spring Cloud Config PropertySource** | 여러 PropertySource를 순회하며 프로퍼티를 탐색 |
| **Spring Batch Chunk Processing** | `ItemReader` → `ItemProcessor` → `ItemWriter` 체인에서 Reader가 이터레이터 역할 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.util.Iterator** | 모든 Collection이 `iterator()`를 제공하는 표준 이터레이터 패턴 |
| **java.lang.Iterable** | `for-each` 문이 내부적으로 `Iterable.iterator()`를 호출 |
| **java.util.stream.Stream** | 내부 반복(Internal Iterator). `stream().filter().map().collect()` |
| **java.util.Spliterator** | 병렬 처리를 위한 분할 가능 이터레이터. Stream의 내부 구현 |
| **java.util.Scanner** | 입력 데이터를 토큰 단위로 순회. `hasNext()`, `next()` |
| **java.util.Enumeration** | 레거시 이터레이터 (Vector, Hashtable에서 사용) |
| **java.io.BufferedReader.lines()** | 파일을 줄 단위로 스트림 순회 |
| **java.nio.file.DirectoryStream** | 디렉토리의 파일을 순회하는 이터레이터 |
| **java.util.ServiceLoader** | SPI 구현체를 순회하는 이터레이터 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **HTTP Pagination (커서 기반)** | `GET /api/items?cursor=abc123&limit=20` → 다음 페이지 커서를 반환하는 이터레이터 패턴 |
| **HTTP Pagination (오프셋 기반)** | `GET /api/items?page=2&size=20` → 페이지별 순회 |
| **GraphQL Cursor Connection** | `first: 10, after: "cursor"` → Relay 스타일 커서 기반 페이지네이션 |
| **WebSocket 메시지 스트림** | 실시간 메시지를 순차적으로 수신하는 이터레이터 패턴 |
| **SSE (Server-Sent Events)** | 서버에서 클라이언트로 이벤트를 순차적으로 스트리밍 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JDBC ResultSet** | `while(rs.next())` → DB 쿼리 결과를 한 행씩 순회하는 커서 기반 이터레이터 |
| **JPA ScrollableResults** | Hibernate의 스크롤 가능한 결과셋으로 대용량 데이터 순회 |
| **MyBatis Cursor** | `@Select` + `Cursor<T>` 반환으로 대용량 결과를 커서로 순회 (메모리 효율) |
| **MongoDB Cursor** | `db.collection.find().batchSize(100)` → 커서로 대용량 문서를 배치 순회 |
| **Redis SCAN** | `SCAN 0 MATCH key* COUNT 100` → 대용량 키를 커서 기반으로 순회 (KEYS 대안) |
| **Elasticsearch Scroll API** | `scroll=1m` → 대용량 검색 결과를 스크롤 커서로 순회 |
| **DB Cursor** | PL/SQL CURSOR, PostgreSQL DECLARE CURSOR → 서버 사이드 커서로 대용량 데이터 순회 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **디렉토리 순회 (ls, find)** | `find . -name "*.java"` → 파일 시스템을 DFS/BFS로 순회 |
| **프로세스 목록 (ps)** | `/proc` 파일시스템을 순회하여 프로세스 정보 수집 |
| **Linux readdir()** | 디렉토리 엔트리를 순차적으로 읽는 시스템 호출 |
| **환경 변수 순회** | `System.getenv()` → 환경 변수를 Map으로 순회 |
| **로그 파일 tail** | `tail -f log.txt` → 파일 끝에 추가되는 내용을 실시간으로 순회 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **트리 순회 (DFS/BFS)** | 전위/중위/후위/레벨 순서 → 각각 다른 이터레이터 구현 |
| **그래프 순회** | DFS(스택), BFS(큐) → 같은 그래프에 대해 다른 순회 전략 |
| **LinkedList Iterator** | 단일/이중 연결 리스트의 순방향/역방향 이터레이터 |
| **Generator/Coroutine** | Python의 `yield`, Kotlin의 `sequence {}` → 지연 평가 이터레이터 |
| **Skip List Iterator** | 여러 레벨의 인덱스를 활용한 효율적 순회 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Consumer poll()** | `consumer.poll(Duration)` → 메시지를 배치로 가져와 순회하는 이터레이터 |
| **RabbitMQ BasicConsume** | 큐에서 메시지를 순차적으로 소비하는 이터레이터 |
| **Spring Cloud Stream** | `@StreamListener`가 메시지 스트림을 순차적으로 소비 |
| **AWS SQS ReceiveMessage** | 큐에서 메시지를 폴링하여 순차 처리하는 이터레이터 패턴 |
