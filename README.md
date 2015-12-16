# JPA_TEST

JPA 프로그래밍 책을 기반 예제 정리 프로젝트

## JPA를 공부하면서 느낀 장점
1. 객체 - 테이블 매핑이 가능. 개발자는 객체만 보고 프로그래밍이 가능하다.
2. 동일 데이터를 로딩할때 퍼시스턴스 컨테이너에서 꺼내온다. (1차 캐시)
3. global lazy fetch 전략을 사용하면 복잡하지 않은 로직을 직접 작성하지 않고 퍼포먼스의 이점을 취할수 있다.

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
