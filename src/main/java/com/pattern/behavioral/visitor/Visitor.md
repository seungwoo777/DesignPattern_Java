# 비지터 패턴 (Visitor Pattern)

## 패턴 개요
객체 구조를 변경하지 않고 새로운 연산을 추가할 수 있게 해주는 패턴. 데이터 구조와 처리를 분리하여 처리 방식만 쉽게 확장한다.

## 특징
- 더블 디스패치(Double Dispatch) 메커니즘 사용
- Element가 `accept(Visitor)`로 Visitor를 받고, Visitor가 `visit(Element)`로 처리
- 새로운 연산(Visitor) 추가는 쉽지만, 새로운 Element 추가는 어려움
- 데이터 구조가 안정적이고 연산이 자주 변경될 때 적합

## 장점
- 개방-폐쇄 원칙(OCP) → 새 연산 추가 용이 (새 Visitor만 추가)
- 단일 책임 원칙(SRP) → 연산 로직을 Visitor에 집중
- 관련 연산을 하나의 Visitor에 모아서 응집도 향상

## 단점
- 새 Element 추가 시 모든 Visitor 수정 필요
- Element의 내부 상태 접근이 필요하여 캡슐화 약화 가능
- 구조가 복잡하고 이해하기 어려움

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **BeanDefinitionVisitor** | Spring Bean 정의를 순회하며 프로퍼티 값의 플레이스홀더(${...})를 치환하는 Visitor |
| **ASM ClassVisitor (컴포넌트 스캔)** | Spring의 `@ComponentScan` 시 바이트코드를 방문하며 어노테이션 메타데이터 수집 |
| **Spring Expression Language (SpEL)** | SpEL 파서가 AST를 Visitor로 순회하며 표현식을 평가 (`SpelNodeImpl.accept()`) |
| **Spring MVC HandlerInterceptor 체인** | 요청 처리 전/후/완료 시점에서 핸들러를 방문하며 각기 다른 처리 수행 |
| **Spring Boot Actuator Endpoint** | 여러 HealthIndicator를 순회하며 상태를 집계하는 CompositeHealthContributor |
| **Spring Data QueryCreator** | 메서드 이름 파싱 트리를 Visitor로 순회하며 쿼리를 생성 |
| **Spring Security AccessDecisionVoter** | 여러 Voter를 순회하며 각 Voter가 접근 허용/거부/보류를 결정 |
| **Spring Integration MessageRouter** | 메시지를 여러 채널에 라우팅할 때 각 채널을 방문하며 조건별 처리 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **javax.lang.model.element.ElementVisitor** | Java 컴파일러 API → 소스코드 AST 요소(패키지/클래스/메서드/필드)를 방문하며 처리 |
| **java.nio.file.FileVisitor** | `Files.walkFileTree()`로 디렉토리 트리를 순회하며 파일별 처리 → JDK의 대표적 Visitor |
| **javax.lang.model.type.TypeVisitor** | 타입 미러를 방문하며 타입별 처리 (DeclaredType, ArrayType, PrimitiveType 등) |
| **java.lang.instrument.ClassFileTransformer** | 클래스 로딩 시 바이트코드를 방문하며 변환 → Agent 기반 Visitor |
| **javax.annotation.processing.AbstractProcessor** | 어노테이션 프로세서가 소스코드 요소를 방문하며 코드 생성/검증 |
| **java.util.stream.Stream API 내부** | 파이프라인의 각 Stage를 Visitor처럼 순회하며 연산(map/filter/reduce) 적용 |
| **DOM API (org.w3c.dom)** | XML/HTML 문서 트리의 노드를 순회하며 처리 (Node, Element, Attribute) |
| **javax.xml.xpath.XPath** | XPath 표현식이 XML 트리를 방문하며 노드를 선택/평가 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **패킷 분석기 (Wireshark/tcpdump)** | 캡처된 패킷의 각 프로토콜 레이어(Ethernet→IP→TCP→HTTP)를 순회하며 분석 |
| **OSI 7계층 처리** | 데이터가 각 계층을 방문하며 헤더 추가(캡슐화) / 제거(역캡슐화) → 계층별 Visitor |
| **HTTP 파이프라인 미들웨어** | 요청이 각 미들웨어를 순차 방문하며 인증→인가→로깅→압축→캐싱 등 처리 |
| **API Gateway Request Pipeline** | 요청이 각 필터를 순회하며 Rate Limiting, 인증, 변환, 라우팅 등 처리 |
| **Network ACL / Firewall Rule** | 패킷이 각 규칙을 순차 방문하며 허용/거부를 판단 |
| **SSL Certificate Chain Validation** | 인증서 체인의 각 인증서를 방문하며 유효성 검증 (Leaf → Intermediate → Root) |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **SQL Parser AST** | SQL 문을 파싱하여 AST를 생성하고, Visitor로 순회하며 쿼리 최적화/실행 계획 생성 |
| **Query Plan Visitor** | 실행 계획 트리(SeqScan, IndexScan, HashJoin 등)를 순회하며 비용 계산/최적화 |
| **Hibernate HQL AST** | HQL을 파싱하여 AST를 생성하고 HqlSqlWalker(Visitor)가 SQL로 변환 |
| **MyBatis SqlNode Visitor** | 동적 SQL의 노드 트리(If, Choose, ForEach, Where)를 순회하며 최종 SQL 생성 |
| **Schema Crawler** | DB 스키마의 테이블/컬럼/인덱스/FK를 순회하며 문서 생성/마이그레이션 스크립트 생성 |
| **JPA Criteria API** | CriteriaQuery의 Expression 트리를 방문하며 JPQL/SQL로 변환 |
| **Apache Calcite (SQL 프레임워크)** | RelNode(관계 대수) 트리를 Visitor로 순회하며 쿼리 최적화 규칙 적용 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **ASM / Javassist / ByteBuddy** | 바이트코드를 ClassVisitor/MethodVisitor/FieldVisitor로 순회하며 분석/변환 → Java 에이전트 핵심 |
| **컴파일러 AST 순회** | 소스코드 AST를 Visitor로 순회하며 타입체크/최적화/코드생성 수행 (Semantic Analysis) |
| **파일시스템 트리 순회** | `find` 명령이 디렉토리 트리를 순회하며 조건별 처리 (삭제/이동/출력/실행) |
| **Kubernetes Admission Controller** | API 요청(Pod/Service/Deployment)을 각 Webhook이 순회하며 검증/변형 |
| **Docker Image Layer Inspection** | 이미지의 각 레이어를 순회하며 보안 취약점 스캔 (Trivy, Clair) |
| **Linux /proc 파일시스템** | /proc 하위 디렉토리를 순회하며 프로세스별 정보(CPU/메모리/fd)를 수집 |
| **Terraform Plan** | 인프라 리소스 그래프를 순회하며 생성/수정/삭제 계획을 수립 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **트리 순회 (Tree Traversal)** | 전위/중위/후위/레벨 순회가 각기 다른 Visitor → 같은 트리 구조에 다른 연산 적용 |
| **그래프 순회 (DFS/BFS)** | 그래프의 각 노드를 방문하며 처리 → 연결 요소 탐색, 위상 정렬, 최단 경로 등 |
| **Composite + Visitor** | Composite 패턴의 트리 구조를 Visitor로 순회 → 구조와 연산의 완전한 분리 |
| **Expression Tree 평가** | 수식 트리를 Visitor로 순회하며 계산/출력/최적화 등 다양한 연산 적용 |
| **패턴 매칭 (Pattern Matching)** | Scala/Kotlin의 sealed class + when이 Visitor 패턴의 함수형 대안 |
| **더블 디스패치 vs 싱글 디스패치** | Java는 싱글 디스패치 → `accept(visitor)` + `visit(element)` 조합으로 더블 디스패치 구현 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Apache Kafka Streams Topology** | 스트림 토폴로지의 각 프로세서 노드를 순회하며 변환/집계/조인 등 처리 |
| **Apache Camel Message Route** | 메시지가 라우트의 각 프로세서를 순차 방문하며 변환/필터/라우팅 처리 |
| **Spring Integration Message Flow** | IntegrationFlow의 각 컴포넌트(Transformer, Filter, Router, Adapter)를 순회하며 메시지 처리 |
| **OpenAPI Spec Walker** | OpenAPI/Swagger 문서의 Path/Operation/Schema를 순회하며 코드생성/문서생성/검증 |
| **Log Pipeline (ELK/Fluentd)** | 로그 이벤트가 파이프라인의 각 필터(grok/mutate/date/geoip)를 순회하며 가공 |
| **Config Tree Processor** | Spring Cloud Config의 설정 트리를 순회하며 프로파일 병합/플레이스홀더 치환/암호화 처리 |
