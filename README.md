# JPA_TEST

JPA 프로그래밍 책을 기반 예제 정리 프로젝트.
모든 예제는 다음의 환경에 최적화되어있습니다.
JDK 1.8.0_60
MariaDB 10.1.8

## JPA를 공부하면서 느낀점
1. 객체 - 테이블 매핑이 가능. 개발자는 객체만 보고 프로그래밍이 가능하다.
2. 동일 데이터를 로딩할때 퍼시스턴스 컨테이너에서 꺼내온다. (1차 캐시)
3. global lazy fetch 전략을 사용하면 복잡하지 않은 로직을 직접 작성하지 않고 퍼포먼스의 이점을 취할수 있다.
3. 1) fetch join을 직접 구현하지 않아도 된다는 점이 아주 마음에 든다.  
3. 2) lazy fetch를 사용한다 하더라도 영속성 컨테이너에 객체가 관리되고 있다면 다시 조회하지 않는다. 굳.
4. 이건 SQL 공부에 도움이 된건데, coalesce, all, any, some 의 사용법을 알게되었다.
5. 상속과 폼되는 객체에 대한 객체와 테이블 설계 매핑되어 테이블 종속적 개발에서 벗어날 수 있다. 이제 클래스 설계할 때 테이블 구조에서 어느정도 자유로워졌다.
6. 왠지 타이핑 칠게 많아진 것 같다.. -> 취소한다. 적어졌다. 
7. IntelliJ에서 static import 단축키 -> ctrl+alt+space, alt+enter -> import statically 선택
8. find() 는 1차 캐쉬 먼저 조회 후 디비 조회 // JPQL은 디비 먼저 조회후 1차 캐쉬 검사
9. JNDI를 사용해서 data source를 WAS에 설정할 수 있다.
10. 단위 테스트 작성 시, given/when/then 의 구분을 사용하면 명확하게 단위테스트를 작성할 수 있다.
11. 도메인 모델 패턴 vs 트랜잭션 스크립트 패턴 의 차이를 배웠다.
12. intellij + wildfly로 sql 파일 실행시킬때 VM option을 줘야한다. (-Dfile.encoding=UTF-8)
13. 컨트롤러를 떠난 순간? 레이지 패치를 사용할 수 없다. OSIV를 사용해야 한다.
14. 블로그 10번 보는 것보다 레퍼런스 1번보는게 낫다. Logback 설정하면서 느낀점.

### 2015년 11월 8일
- JPA + MariaDB 를 사용하여 예제 구현

### 2015년 11월 9일
- Date type의 활용
- Enum type의 활용
- PK(sequence, auto increment)의 활용
- unique 제약조건
- 실전 예제

### 2015년 11월 12일
- 다대일, 일대다, 다대다, 일대일 연관관계 기초
- 연관관계 주인
- 연관관계 편의 메소드

### 2015년 11월 17일
- 메이븐 모듈 프로젝트 컨버팅
- 일대다, 다대일 바이디렉션

### 2015년 11월 18일
- 각 단원별로 메이븐 모듈 구성.
- ManyToMany (identifying, non-identifying)
- 실전예제 (enterprise architect + JPA + MariaDB)

### 2015년 11월 21일
- 상속 매핑 (joined table, single table, table per class)
- mapped superclass

### 2015년 11월 22일
- identifying, non-identifying
- multi key
- idClass, embeddedId
- one-to-one identifying

### 2015년 11월 27일
- proxy
- eager fatch
- lazy fatch
- use lazy fatch

### 2015년 11월 28일
- 영속성 전이
-  persist
-  remove
- 고아객체 (orphan)
- 실전예제

### 2015년 11월 28일
- Embeddable, Embedded
- AttributeOverride
- immutable
- ElementCollection, CollectionTable
- 실전예제

### 2015년 12월 1일
- JPQL
- Criteria
- QueryDSL
- Native SQL
- get JDBC connection

### 2015년 12월 4일
- 초기 데이터 세팅 (META-INF/import.sql 파일 classes 안으로 옮겨야함)
- Query, TypedQuery
- Parameter binding
- Projection
- Entity
- Embedded
- Scala
- Select muliple column
- New keyword

### 2015년 12월 8일
- paging API
- set and ordering
- group by, having
- order by
- join
- inner join
- outer join
- collection join

### 2015년 12월 16일
- theta join
- join on
- fetch join
- entity fetch join
- collection fetch join
- distinct in collection fetch join
- difference fetch join and normal join
- path expression

### 2015년 12월 26일
- sub query
- sub query function (all, any, some)
- condition expression
- in, like
- collection expression (empty, is null, scala, case(coalesce vs isnull), nullif
- polymorphism query (type(), treat())
- custom function
- using direct entity
- named query (annotation, xml)

### 2016년 1월 2일

- import 세팅... s 하나 빠진 것이 문제였음.
- QueryDSL 설정
- start
- create basic Q
- search condition query

### 2016년 1월 9일

- 결과 조회
- paging & ordering
- grouping
- joining
- sub query
- projection
- distinct

### 2016년 1월 10일

[QueryDSL]
- update, remove batch
- dynamic query
- method delegation

[Native SQL]
- inquiry entity
- inquiry value
- inquiry entity + value (SqlResultSetMapping)

### 2016년 1월 25일

- NamedNativeQuery -> basic, annotation, xml
- StoredProcedureQuery -> basic, annotation, xml
- bulk operation -> insert, update, delete
- persistent context & JPQL -> find() vs JPQL
- JPQL & flush mode -> FlushModeType.AUTO, FlushModeType.COMMIT

### 2016년 1월 30일

- Spring + JPA 설정
- Wildfly + JNDI 설정
- MariaDB 설정
- logback 설정

### 2016년 2월 9일

- Enterprise 를 활용한 모델링 진행

### 2016년 2월 10일

- hibernate + spring 세팅 수정 (오타를 조심하자) 
- EA를 사용, 도메인 모델링 완료. 
- 연관관계 정의 - Repository, Service 구현 
- 주요 단위 테스트 작성

### 2016년 2월 11일

- 컨트롤러 구현 중
- jsp 구현 중
- import.sql 파일 추가

### 2016년 2월 13일

- wildfly9 cli 를 이용한 jsp hot deploy 설정
- 주문 리스트 검색, 주문하기 기능 구현
- Logback 설정(STDOUT, FILE)
- Spring data JPA 설정
- 메소드 이름으로 쿼리 생성
- JPA namedQuery
- @Query, 리포지토리 메소드에 쿼리 정의
- 파라미터 바인딩
- 벌크성 수정 쿼리
- 반환 타입
- 페이징과 정렬
- 힌트
- 락
