# 인터프리터 패턴 (Interpreter Pattern)

## 패턴 개요
간단한 언어의 문법을 정의하고, 그 문장을 해석하는 인터프리터를 구현하는 패턴. 각 문법 규칙을 클래스로 표현한다.

## 특징
- 문법 규칙을 클래스 계층으로 표현
- 종단 표현식(Terminal)과 비종단 표현식(Non-Terminal)으로 구성
- 추상 구문 트리(AST)를 순회하며 해석
- 간단한 DSL(Domain Specific Language) 구현에 적합

## 장점
- 문법 추가/변경이 용이
- 각 규칙이 독립적인 클래스로 분리 (SRP)

## 단점
- 복잡한 문법에는 클래스 수가 급격히 증가
- 성능이 중요한 경우 부적합

---

## 실무 적용 사례

### Spring Framework
| 적용 위치 | 설명 |
|-----------|------|
| **SpEL (Spring Expression Language)** | `@Value("#{systemProperties['user.name']}")`, `@PreAuthorize("hasRole('ADMIN') and #id > 0")` → 표현식을 AST로 파싱하고 인터프리터로 평가 |
| **Thymeleaf 템플릿 엔진** | `th:if="${user.age > 18}"`, `th:each="item : ${list}"` → 템플릿 문법을 인터프리터로 해석 |
| **JPA Criteria API** | `cb.and(cb.equal(root.get("name"), "test"), cb.gt(root.get("age"), 20))` → 쿼리 조건을 표현식 트리로 구성 |
| **QueryDSL** | `entity.name.eq("test").and(entity.age.gt(20))` → 타입 세이프 쿼리 표현식을 인터프리터로 SQL 변환 |
| **Spring Cloud Gateway Predicate** | `Path=/api/**, Header=X-Token, \d+` → 라우팅 조건을 인터프리터로 평가 |
| **Spring Batch Conditional Flow** | `on("COMPLETED").to(step2).from(step1).on("FAILED").to(errorStep)` → 흐름 조건을 인터프리터로 평가 |
| **Property Placeholder** | `${db.host:localhost}` → 플레이스홀더를 인터프리터로 해석하여 실제 값으로 치환 |

### Java / JDK
| 적용 위치 | 설명 |
|-----------|------|
| **java.util.regex.Pattern** | 정규표현식을 NFA/DFA로 컴파일하고 인터프리터로 매칭. `Pattern.compile("\\d+").matcher("123")` |
| **java.text.Format (MessageFormat, DecimalFormat)** | `MessageFormat.format("{0} has {1} items", name, count)` → 포맷 문법을 인터프리터로 해석 |
| **javax.script.ScriptEngine** | JavaScript/Groovy 스크립트를 인터프리터로 실행. `engine.eval("1 + 2")` |
| **java.util.Formatter** | `String.format("%s has %d items", name, count)` → printf 스타일 포맷 문법 해석 |
| **java.text.SimpleDateFormat** | `"yyyy-MM-dd HH:mm:ss"` → 날짜 포맷 패턴을 인터프리터로 해석 |
| **Java Compiler API (javax.tools)** | Java 소스를 AST로 파싱하고 바이트코드로 컴파일하는 인터프리터 |

### 네트워크
| 적용 위치 | 설명 |
|-----------|------|
| **URL 파싱** | `scheme://host:port/path?query#fragment` → URL 문법을 인터프리터로 파싱 |
| **HTTP 헤더 파싱** | `Content-Type: application/json; charset=utf-8` → 헤더 문법을 인터프리터로 해석 |
| **CIDR 표기법** | `192.168.1.0/24` → IP/서브넷 표기를 인터프리터로 해석하여 네트워크 범위 계산 |
| **cron 표현식** | `0 0 12 * * ?` → 스케줄 문법을 인터프리터로 해석하여 다음 실행 시간 계산 |
| **GraphQL 쿼리 파서** | `{ user(id: 1) { name age } }` → GraphQL 문법을 AST로 파싱하고 실행 |
| **SQL 파서** | SQL 문을 토크나이저 → 파서 → AST → 실행계획으로 변환하는 인터프리터 체인 |

### 데이터베이스
| 적용 위치 | 설명 |
|-----------|------|
| **SQL 엔진** | SELECT/INSERT/UPDATE/DELETE 문을 파싱 → AST 생성 → 최적화 → 실행하는 인터프리터 |
| **Elasticsearch Query DSL** | `{"bool":{"must":[{"match":{"field":"value"}}]}}` → JSON 쿼리를 인터프리터로 해석 |
| **MongoDB Query** | `db.users.find({age: {$gt: 20}, name: /^Kim/})` → 쿼리 문법을 인터프리터로 해석 |
| **Redis Lua Script** | `EVAL "return redis.call('get',KEYS[1])" 1 mykey` → Lua 스크립트를 Redis 내장 인터프리터로 실행 |
| **Stored Procedure** | PL/SQL, PL/pgSQL 등 절차적 언어를 DB 내장 인터프리터로 실행 |
| **JPA JPQL 파서** | `SELECT u FROM User u WHERE u.age > :age` → JPQL을 파싱하여 네이티브 SQL로 변환 |

### 운영체제 / 시스템
| 적용 위치 | 설명 |
|-----------|------|
| **Shell 인터프리터 (Bash/Zsh)** | 셸 스크립트를 토크나이징 → 파싱 → AST → 실행하는 인터프리터 |
| **정규표현식 엔진 (grep, sed, awk)** | 텍스트 패턴을 정규식으로 해석하여 매칭/치환 |
| **YAML/JSON/TOML 파서** | 설정 파일 문법을 파싱하여 Key-Value 구조로 변환 |
| **Dockerfile 파서** | `FROM`, `RUN`, `COPY` 등 Dockerfile 명령어를 인터프리터로 해석하여 이미지 빌드 |
| **Makefile 파서** | 타겟:의존성\n\t명령 형식을 인터프리터로 해석하여 빌드 실행 |
| **Gradle/Maven 빌드 스크립트** | Groovy/XML DSL을 인터프리터로 해석하여 빌드 태스크 실행 |

### 자료구조 / 알고리즘
| 적용 위치 | 설명 |
|-----------|------|
| **수식 트리 (Expression Tree)** | 수학 수식을 트리로 파싱하고 순회하며 계산. 계산기의 핵심 자료구조 |
| **AST (Abstract Syntax Tree)** | 프로그래밍 언어를 파싱한 추상 구문 트리 → 컴파일러/인터프리터의 핵심 |
| **BNF / EBNF 문법** | 언어 문법을 형식적으로 정의하는 메타 언어 → 인터프리터 패턴의 이론적 기반 |
| **Recursive Descent Parser** | 각 문법 규칙을 재귀 함수로 구현하는 하향식 파서 → 인터프리터 패턴의 구현 기법 |

### 메시징 / 미들웨어
| 적용 위치 | 설명 |
|-----------|------|
| **Kafka KSQL** | `SELECT * FROM stream WHERE amount > 100` → 스트리밍 데이터를 SQL-like 문법으로 인터프리팅 |
| **Apache Camel Simple Language** | `${header.type} == 'ORDER'` → 라우팅 조건을 인터프리터로 평가 |
| **Spring Integration SpEL** | 메시지 라우팅/필터링 조건을 SpEL 표현식으로 정의하고 인터프리터로 평가 |
