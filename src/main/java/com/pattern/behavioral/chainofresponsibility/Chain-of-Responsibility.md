# 책임 연쇄 패턴 (Chain of Responsibility Pattern)

## 패턴 개요
요청을 처리할 수 있는 핸들러들을 체인으로 연결하여, 요청이 처리될 때까지 순서대로 전달하는 패턴

## 특징
- 핸들러들이 체인으로 연결되어 요청을 순차 처리
- 각 핸들러는 요청을 직접 처리하거나 다음 핸들러로 전달
- 요청 발신자와 수신자를 분리 (느슨한 결합)
- 런타임에 체인 구성을 동적으로 변경 가능

## 장점
- 요청 처리 순서를 유연하게 변경 가능
- 단일 책임 원칙(SRP) → 각 핸들러가 하나의 처리만 담당
- 개방-폐쇄 원칙(OCP) → 새 핸들러 추가가 쉬움

## 단점
- 요청이 처리되지 않고 체인 끝까지 도달할 수 있음
- 디버깅이 어려울 수 있음 (어떤 핸들러가 처리했는지 추적 필요)
- 체인이 길어지면 성능 저하

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Security FilterChain** | `UsernamePasswordAuthenticationFilter` → `BasicAuthenticationFilter` → `BearerTokenAuthenticationFilter` → `AuthorizationFilter` 순서로 보안 처리 |
| **Servlet Filter Chain** | `FilterChain.doFilter(request, response)` → 여러 Filter가 순서대로 요청을 처리하고 `chain.doFilter()`로 다음 필터에 전달 |
| **HandlerInterceptor** | `preHandle()` → Controller → `postHandle()` → `afterCompletion()` 인터셉터 체인 |
| **Spring MVC ExceptionResolver** | 예외 발생 시 `ExceptionHandlerExceptionResolver` → `ResponseStatusExceptionResolver` → `DefaultHandlerExceptionResolver` 순서로 처리 시도 |
| **Spring Cloud Gateway Filter** | Pre Filter 체인 → 라우팅 → Post Filter 체인으로 요청 처리 |
| **Spring Validation** | 여러 Validator를 체인으로 연결하여 순차적으로 검증 |
| **Spring Batch Skip/Retry Policy** | 여러 정책을 체인으로 연결하여 순차 평가 |
| **BeanPostProcessor 체인** | 여러 BeanPostProcessor가 순서대로 Bean 초기화에 개입 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.util.logging.Handler 체인** | Logger에 여러 Handler(ConsoleHandler, FileHandler, SocketHandler)를 체인으로 연결 |
| **ClassLoader 위임 모델** | Custom → Application → Extension → Bootstrap ClassLoader 순으로 클래스 로딩 시도 (Parent Delegation) |
| **try-catch 체인** | `catch (IOException)` → `catch (SQLException)` → `catch (Exception)` 순서로 예외 핸들러 체인 |
| **java.awt.EventQueue** | AWT/Swing 이벤트를 이벤트 큐에서 꺼내 핸들러 체인으로 전달 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **OSI 7계층** | Physical → DataLink → Network → Transport → Session → Presentation → Application 각 계층이 패킷을 처리하고 다음 계층으로 전달 |
| **TCP/IP 프로토콜 스택** | NIC → Ethernet → IP → TCP → Application 순서로 패킷을 처리 |
| **Netty ChannelPipeline** | `ChannelInboundHandler` → `ChannelOutboundHandler` 핸들러들이 파이프라인으로 연결 |
| **Nginx location 매칭** | `location` 블록이 순서대로 URL 패턴을 매칭하여 요청 처리 |
| **iptables/nftables** | PREROUTING → INPUT → FORWARD → OUTPUT → POSTROUTING 체인으로 패킷 처리 |
| **API Gateway Rate Limiting** | IP 기반 → User 기반 → API Key 기반 Rate Limit를 순차 적용 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **쿼리 실행 계획 (Query Optimizer)** | 파싱 → 의미 분석 → 최적화 → 실행 계획 생성 → 실행의 처리 체인 |
| **MyBatis Interceptor Chain** | 여러 Interceptor가 `Executor` → `StatementHandler` → `ParameterHandler` → `ResultSetHandler` 순으로 체인 처리 |
| **Hibernate Event Listener** | PreInsertEventListener → PreUpdateEventListener → PostInsertEventListener 등 이벤트 리스너 체인 |
| **데이터 파이프라인** | Extract → Transform → Load (ETL) 체인 |
| **Trigger 체인** | BEFORE INSERT → 실행 → AFTER INSERT 순서로 트리거 체인 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Linux 시그널 핸들러** | SIGINT, SIGTERM 등 시그널을 핸들러 체인으로 처리 |
| **Unix Pipe** | `cat file \| grep "error" \| sort \| uniq -c` → 각 프로세스가 출력을 다음 프로세스로 전달 |
| **Linux I/O 스케줄러** | 여러 I/O 요청을 스케줄러 체인으로 처리 (noop → deadline → cfq) |
| **systemd 의존성 체인** | 서비스 시작 순서를 의존성 체인으로 관리 (After=, Requires=) |
| **Middleware 스택** | 웹 프레임워크의 미들웨어가 요청 처리 체인을 구성 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **Linked List 순회** | 각 노드가 다음 노드로의 참조를 가지며 순차적으로 탐색하는 구조 자체가 체인 |
| **Hash Collision Chain** | HashMap에서 해시 충돌 시 Chaining 방식으로 같은 버킷의 엔트리를 연결 리스트로 체인 |
| **State Machine 전이** | 현재 상태 → 조건 평가 → 다음 상태로의 전이를 체인으로 처리 |
| **Pipeline 패턴** | 데이터를 여러 단계(Stage)를 거쳐 순차 변환하는 파이프라인 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Streams Topology** | Source → Processor → Processor → Sink 순서로 스트림 처리 체인 |
| **Spring Integration MessageHandlerChain** | 여러 MessageHandler를 체인으로 연결하여 순차 처리 |
| **Apache Camel Route** | `from("direct:start").process(...).to("kafka:topic")` → EIP 라우팅 체인 |
| **RabbitMQ Exchange Routing** | Exchange → Binding → Queue 순서로 메시지 라우팅 체인 |
