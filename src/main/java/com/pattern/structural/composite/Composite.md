# 컴포지트 패턴 (Composite Pattern)

## 패턴 개요
객체들을 트리 구조로 구성하여 개별 객체와 복합 객체를 클라이언트가 동일하게 다룰 수 있게 하는 패턴

## 특징
- 트리 구조로 부분-전체(Part-Whole) 계층 표현
- Leaf(단일 객체)와 Composite(복합 객체)를 동일한 인터페이스(Component)로 처리
- 재귀적 구조로 무한 중첩 가능
- 클라이언트는 단일/복합 객체를 구분하지 않고 사용

## 장점
- 복잡한 트리 구조를 단순하게 다룰 수 있음
- 새로운 Leaf/Composite 타입 추가 용이 (OCP 준수)
- 클라이언트 코드 단순화 (다형성 활용)

## 단점
- 지나치게 일반적인 설계가 될 수 있음
- Leaf와 Composite의 기능 차이가 클 때 처리 복잡
- 타입 안전성 보장이 어려울 수 있음

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **Spring Security FilterChain** | 여러 Filter를 체인(CompositeFilter)으로 묶어 하나의 필터처럼 처리 |
| **CompositeHealthIndicator** | 여러 HealthIndicator를 묶어 전체 헬스체크 결과를 하나로 반환. `/actuator/health` |
| **CompositeCacheManager** | 여러 CacheManager(Redis + Caffeine)를 하나로 묶어 순차 조회 |
| **CompositePropertySource** | 여러 PropertySource(application.yml, 환경변수, Config Server)를 트리로 구성 |
| **Spring Batch CompositeItemProcessor** | 여러 ItemProcessor를 하나로 체이닝하여 순차 처리 |
| **Spring Integration MessageHandlerChain** | 여러 MessageHandler를 하나의 핸들러처럼 순차 실행 |
| **Spring AOP CompositePointcut** | 여러 Pointcut을 AND/OR로 조합하여 하나의 Pointcut처럼 사용 |
| **BeanFactoryPostProcessor** | 여러 PostProcessor를 순차적으로 적용하는 Composite 구조 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.awt.Container / Component** | Swing/AWT의 GUI 컴포넌트 트리. Container(Panel, Frame)가 Component(Button, Label)를 포함 |
| **javax.swing.JComponent** | JPanel 안에 JButton, JLabel, 또 다른 JPanel을 중첩하는 트리 구조 |
| **java.io.File** | File(파일)과 Directory(디렉토리)를 동일 인터페이스로 처리. `listFiles()`로 재귀 탐색 |
| **java.nio.file.Path** | 파일 경로를 트리 구조로 표현. `resolve()`, `relativize()`로 조합 |
| **ClassLoader 계층** | Bootstrap → Extension → Application → Custom ClassLoader가 부모-자식 트리 구조 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **DNS 도메인 트리** | 루트(.) → TLD(.com) → SLD(example.com) → 서브도메인이 트리 구조 |
| **IP 서브넷 계층** | 네트워크를 서브넷으로 분할하여 트리 구조로 라우팅 (CIDR) |
| **로드밸런서 트리** | Global LB → Regional LB → Local LB → 서버 인스턴스의 계층 구조 |
| **CDN 계층** | Origin → Edge Server → PoP(Point of Presence)의 트리 구조 |
| **방화벽 규칙 그룹** | 여러 규칙을 그룹으로 묶고, 그룹 안에 하위 그룹을 중첩하는 컴포지트 구조 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **계층형 쿼리 (Hierarchical Query)** | Oracle `CONNECT BY`, PostgreSQL `WITH RECURSIVE` → 트리 데이터 조회 |
| **JPA Criteria Predicate** | `cb.and(predicate1, cb.or(predicate2, predicate3))` → 조건식을 트리로 조합 |
| **QueryDSL BooleanExpression** | `.and().or()` 체이닝으로 쿼리 조건을 트리 구조로 조합 |
| **Elasticsearch Query DSL** | `bool: { must: [...], should: [...] }` → 검색 조건을 트리로 중첩 |
| **MongoDB Aggregation Pipeline** | `$match` → `$group` → `$sort` → 파이프라인을 조합하여 트리 처리 |
| **인접 리스트 / 중첩 집합 모델** | DB에서 트리 데이터를 저장하는 패턴 (Adjacency List, Nested Set, Materialized Path, Closure Table) |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **파일 시스템** | 디렉토리(Composite)가 파일(Leaf)과 하위 디렉토리를 포함하는 대표적 컴포지트 |
| **프로세스 트리** | 부모 프로세스 → 자식 프로세스의 트리 구조. `pstree` 명령어로 확인 |
| **Linux cgroup** | Control Group이 트리 구조로 리소스(CPU/메모리)를 계층적으로 제한 |
| **Kubernetes 네임스페이스** | 클러스터 → 네임스페이스 → Pod → Container 계층 구조 |
| **XML/HTML DOM** | 문서의 노드(Element, Text, Attribute)가 트리 구조를 형성 |
| **AST (Abstract Syntax Tree)** | 소스코드를 파싱한 추상 구문 트리가 대표적 컴포지트 구조 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **트리 자료구조 자체** | B-Tree, Red-Black Tree, AVL Tree → 노드가 자식 노드를 포함하는 컴포지트 |
| **Trie (접두사 트리)** | 문자열 검색을 위한 트리 → 각 노드가 자식 노드를 재귀적으로 포함 |
| **수식 트리 (Expression Tree)** | `(1 + 2) * 3` → 연산자(Composite) + 피연산자(Leaf)의 트리 |
| **JSON/YAML 구조** | 객체/배열(Composite) + 원시값(Leaf)의 중첩 트리 구조 |
| **Composite 패턴 + Iterator** | 트리 구조를 DFS/BFS로 순회하는 Iterator와 조합하여 사용 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka Topic Partition** | Topic(Composite) → Partition(Leaf)의 2단계 트리 |
| **RabbitMQ Exchange Binding** | Exchange(Composite)가 Queue(Leaf)와 하위 Exchange를 바인딩하는 트리 |
| **Spring Cloud Config 계층** | application.yml(기본) → application-{profile}.yml(오버라이드) → 서비스별 설정의 계층적 오버라이드 |
