# 프록시 패턴 (Proxy Pattern)

## 패턴 개요
대상 객체에 대한 접근을 제어하는 대리 객체를 제공하는 패턴. 접근 제어, 지연 초기화, 로깅 등 원본 객체를 수정하지 않고 부가 기능을 추가할 수 있다.

## 특징
- 대상 객체와 동일한 인터페이스를 구현하는 프록시 객체
- 종류: 보호 프록시, 가상 프록시(지연 초기화), 원격 프록시, 로깅 프록시, 캐싱 프록시
- 정적 프록시 vs 동적 프록시 (JDK Dynamic Proxy, CGLIB, ByteBuddy)
- 프록시는 흐름 제어만 하고 결과값을 조작하지 않음

## 장점
- 개방-폐쇄 원칙(OCP) 준수
- 단일 책임 원칙(SRP) 준수
- 클라이언트는 프록시와 실제 객체를 구분할 필요 없음

## 단점
- 프록시 클래스 수 증가 (동적 프록시로 해결 가능)
- 응답 지연 가능성
- 디버깅 시 프록시 체인 추적이 복잡할 수 있음

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring AOP (핵심!)** | `@Transactional`, `@Cacheable`, `@Async`, `@Secured` 등 모든 AOP 어노테이션이 프록시 기반으로 동작. 인터페이스 있으면 JDK Dynamic Proxy, 없으면 CGLIB 사용 |
| **JPA Lazy Loading** | 연관 엔티티를 Hibernate Proxy로 감싸서 실제 접근 시에만 DB 조회 (가상 프록시). `LazyInitializationException`의 원인 |
| **Spring Security Method Security** | `@PreAuthorize`, `@Secured`가 프록시를 통해 메서드 호출 전 권한 검사 (보호 프록시) |
| **@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)** | Request/Session 스코프 빈을 싱글톤에 주입할 때 스코프 프록시 사용 |
| **Spring Cloud OpenFeign** | 인터페이스만 선언하면 동적 프록시가 HTTP 호출로 변환 (원격 프록시) |
| **Spring Retry @Retryable** | 메서드 호출을 프록시가 가로채서 실패 시 재시도 |
| **Spring Data Repository** | 인터페이스만 선언하면 `SimpleJpaRepository` 프록시가 CRUD 구현을 자동 생성 |
| **Spring WebSocket** | `SockJS`가 WebSocket 미지원 브라우저를 위한 폴백 프록시 역할 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.lang.reflect.Proxy** | JDK 동적 프록시 → 인터페이스 기반 프록시 객체를 런타임에 생성. `InvocationHandler`와 함께 사용 |
| **RMI (Remote Method Invocation)** | Stub(클라이언트 프록시)이 원격 객체의 메서드 호출을 네트워크로 전달 (원격 프록시) |
| **java.util.Collections.unmodifiableList()** | 수정 연산을 차단하는 보호 프록시 (UnsupportedOperationException 발생) |
| **java.util.concurrent.FutureTask** | 비동기 작업의 결과를 프록시하여 완료 시점에 접근 (가상 프록시) |
| **java.lang.reflect.Method** | `method.invoke()`가 대상 메서드를 프록시하여 호출 |
| **SecurityManager** | JVM 보안 관리자가 시스템 리소스 접근을 제어하는 보호 프록시 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **Forward Proxy** | 클라이언트가 프록시를 통해 외부 서버에 접근. 익명성/캐싱/필터링 (Squid, Privoxy) |
| **Reverse Proxy** | 서버 앞에서 요청을 받아 로드밸런싱/SSL 종료/캐싱 처리 (Nginx, HAProxy, Envoy) |
| **API Gateway** | 모든 API 요청을 프록시하여 인증/라우팅/Rate Limiting/로깅 처리 |
| **Service Mesh Sidecar** | Istio/Envoy 사이드카 프록시가 Pod 간 통신을 중개 (mTLS, 트래픽 관리, 관측성) |
| **CDN (Content Delivery Network)** | 오리진 서버의 콘텐츠를 엣지에서 캐싱하여 대신 제공하는 캐싱 프록시 |
| **NAT (Network Address Translation)** | 사설 IP를 공인 IP로 변환하는 네트워크 레벨 프록시 |
| **VPN** | 네트워크 트래픽을 VPN 서버를 통해 프록시하여 암호화/우회 |
| **DNS Proxy** | DNS 쿼리를 프록시하여 캐싱/필터링 (Pi-hole, CoreDNS) |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **Hibernate Proxy (Lazy Loading)** | 연관 엔티티를 프록시로 감싸서 `get{Property}()` 호출 시에만 SELECT 실행 |
| **Connection Pool Proxy** | HikariCP의 `HikariProxyConnection`이 실제 Connection을 프록시하여 풀 반환/통계 관리 |
| **P6Spy / Log4JDBC** | JDBC Driver를 프록시하여 SQL 쿼리 로깅/성능 측정 (로깅 프록시) |
| **ProxySQL / PgBouncer** | DB 앞에서 커넥션 풀링, 쿼리 라우팅, 읽기/쓰기 분리를 처리하는 DB 프록시 |
| **Read Replica 라우팅** | Spring의 `AbstractRoutingDataSource`가 읽기/쓰기에 따라 다른 DataSource로 프록시 |
| **Redis Proxy (Twemproxy)** | 여러 Redis 인스턴스를 하나의 엔드포인트로 프록시하여 샤딩 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Virtual Memory (가상 메모리)** | 물리 메모리를 가상 주소로 프록시하여 접근. 페이지 폴트 시에만 실제 메모리 할당 (가상 프록시) |
| **Lazy Loading (mmap)** | 파일을 메모리 매핑하되 실제 접근 시에만 디스크에서 로딩 |
| **FUSE (Filesystem in Userspace)** | 사용자 공간에서 파일시스템을 프록시하여 커스텀 처리 (S3FS, SSHFS 등) |
| **cglib / ByteBuddy** | 바이트코드 조작으로 클래스 기반 프록시 생성. Spring이 내부적으로 사용 |
| **LD_PRELOAD** | 공유 라이브러리 함수를 프록시하여 동작 변경 (디버깅, 성능 측정) |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Lazy Evaluation** | 연산 결과를 프록시하여 실제 필요할 때까지 계산을 지연. Java Stream의 중간 연산이 대표적 |
| **Copy-on-Write** | 쓰기 시에만 실제 복사하는 프록시. `CopyOnWriteArrayList`, `CopyOnWriteArraySet` |
| **Smart Pointer (C++)** | `shared_ptr`, `unique_ptr`이 원시 포인터를 프록시하여 메모리 관리 자동화 |
| **Cache Proxy** | 계산 결과를 캐싱하여 동일 입력에 대해 재계산 방지 (Memoization) |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka MirrorMaker** | 클러스터 간 토픽을 프록시하여 복제 |
| **RabbitMQ Federation** | 원격 브로커의 큐를 프록시하여 로컬에서 소비 가능하게 함 |
| **Spring Cloud Gateway** | 모든 요청을 프록시하여 필터링/라우팅/로드밸런싱 |
| **Envoy Proxy** | L4/L7 프록시로 서비스 간 통신을 중개 (관측성, 보안, 트래픽 관리) |
