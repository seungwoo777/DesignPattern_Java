# 프로토타입 패턴 (Prototype Pattern)

## 패턴 개요
기존 객체를 복제(clone)하여 새로운 객체를 생성하는 패턴. 객체 생성 비용이 클 때 원본을 복제하여 효율적으로 생성한다.

## 특징
- 기존 객체를 복제하여 새 객체 생성 (new 대신 clone)
- 깊은 복사(Deep Copy) vs 얕은 복사(Shallow Copy) 구분 필요
- Java의 `Cloneable` 인터페이스와 `clone()` 메서드 활용
- 객체 생성 비용이 비쌀 때(DB 조회, 네트워크 요청 등) 효과적
- Prototype Registry와 함께 사용하면 프로토타입 관리 용이

## 장점
- 복잡한 객체 생성 과정을 반복하지 않아도 됨
- 런타임에 객체 타입을 동적으로 복제 가능
- 초기화 비용 절감

## 단점
- 깊은 복사 구현이 복잡할 수 있음 (순환 참조, 복잡한 객체 그래프)
- Cloneable 인터페이스의 설계적 한계 (Effective Java에서 비권장)

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **@Scope("prototype")** | 매번 새로운 인스턴스를 생성하는 Prototype Scope Bean. `ObjectProvider<T>`나 `@Lookup`으로 주입 |
| **BeanDefinition 복사** | Spring 내부에서 Bean 정의를 복사하여 재활용. `GenericBeanDefinition.cloneBeanDefinition()` |
| **ObjectMapper.copy()** | Jackson ObjectMapper의 설정을 복제하여 커스텀 설정 적용 |
| **Spring Batch ExecutionContext** | 배치 실행 컨텍스트를 복사하여 Step 간 데이터 전달 |
| **MockMvc 테스트** | 기본 MockMvc 설정을 복제하여 테스트별로 커스터마이징 |
| **Spring Security Authentication** | Authentication 토큰을 복사하여 권한 정보를 추가/수정 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **Object.clone()** | Java의 기본 clone 메커니즘. `Cloneable` 구현 + `clone()` 오버라이드 |
| **Arrays.copyOf()** | 배열을 복사하여 새로운 배열 생성 |
| **ArrayList(Collection)** | 생성자로 기존 컬렉션을 복사하여 새로운 리스트 생성 |
| **java.util.Date.clone()** | Date 객체를 복제하여 불변성 보장 (방어적 복사) |
| **SerializationUtils (Apache Commons)** | 직렬화/역직렬화를 통한 깊은 복사 |
| **Collections.copy()** | 컬렉션의 원소를 다른 컬렉션으로 복사 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **HTTP 요청 복제** | 재시도(Retry) 시 원본 HTTP 요청을 복제하여 재전송. OkHttp `Request.newBuilder()` |
| **Protobuf Message.toBuilder()** | Protobuf 메시지를 복제하여 일부 필드만 수정 후 재전송 |
| **gRPC 메타데이터 복제** | 요청 메타데이터를 복제하여 인터셉터에서 헤더 추가 |
| **WebSocket 메시지 복제** | 브로드캐스트 시 메시지를 복제하여 각 클라이언트에 전달 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **JPA Detached Entity 복사** | 영속 상태의 엔티티를 복제하여 새로운 엔티티로 저장 (재주문, 복사 기능) |
| **Hibernate Dirty Checking** | 엔티티의 원본 스냅샷(Hydrated State)을 보관하여 변경 감지에 사용 → 내부적 프로토타입 |
| **쿼리 템플릿 복제** | PreparedStatement의 쿼리 템플릿을 복제하여 파라미터만 변경 후 실행 |
| **MyBatis Parameter 복제** | 매퍼 파라미터 객체를 복제하여 동적 쿼리 조건을 변경 |
| **Redis Object 복제** | 캐시된 객체를 복제하여 원본 캐시 데이터의 오염 방지 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Unix fork()** | 프로세스를 복제하여 자식 프로세스 생성 → OS 수준의 프로토타입 패턴 |
| **Copy-on-Write (COW)** | Linux에서 fork 시 메모리 페이지를 공유하다가 수정 시에만 복사 → 최적화된 프로토타입 |
| **Docker Image Layer** | 베이스 이미지를 복제하여 레이어를 추가하는 구조 |
| **VM Snapshot / Template** | VM 템플릿을 복제하여 새 인스턴스 생성 (AWS AMI, VMware Template) |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **방어적 복사 (Defensive Copy)** | 불변 객체를 보장하기 위해 내부 가변 필드를 복사하여 반환 (Effective Java Item 50) |
| **Persistent Data Structure** | 함수형 프로그래밍의 불변 자료구조 → 수정 시 기존 구조를 공유하며 새 버전 생성 |
| **MapStruct / ModelMapper** | 객체 간 필드 매핑을 통한 복사. DTO ↔ Entity 변환에서 프로토타입 개념 활용 |
| **A* 알고리즘 상태 복제** | 탐색 알고리즘에서 현재 상태를 복제하여 각 경로를 독립적으로 탐색 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **이벤트 메시지 Fan-out** | 하나의 이벤트 메시지를 복제하여 여러 큐/토픽으로 Fan-out 전송 |
| **Kafka Record 재처리** | Dead Letter Queue의 메시지를 복제하여 재처리 토픽으로 전송 |
| **Spring Integration Message 복제** | 메시지를 복제하여 여러 채널로 분기 (Splitter 패턴) |
