# Mission09 - 연관관계 & 트랜잭션

작성자: 김성관

## 과제 목표

- `@ManyToOne`/`@OneToMany`로 Team-Member 1:N 연관관계를 매핑한다
- `@Transactional`을 Service 계층에 적용하여 트랜잭션을 관리한다
- 팀별 멤버 조회 등 연관관계 기반 API를 구현한다

## 도메인 구조

- `Team (1) - Member (N)`: 팀 하나에 여러 멤버가 소속된다. 연관관계의 주인은 N쪽인 `Member`이며, `team_id` 외래키를 `@JoinColumn`으로 관리한다.
- `Member (1) - Assignment (N)`: 멤버 한 명에게 여러 과제가 할당된다. 연관관계의 주인은 N쪽인 `Assignment`이며, `member_id` 외래키를 `@JoinColumn`으로 관리한다.

## 실행 방법

```
gradle bootRun
```

H2 인메모리 데이터베이스를 사용하며, 애플리케이션 실행 시 스키마가 자동 생성됩니다.

## API

### Team

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /teams | 팀 생성 |
| GET | /teams | 팀 전체 조회 |
| GET | /teams/{id} | 팀 단건 조회 |
| GET | /teams/{id}/members | 팀별 멤버 조회 |
| PUT | /teams/{id} | 팀 수정 |
| DELETE | /teams/{id} | 팀 삭제 |

### Member

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /members | 멤버 생성 (소속 팀 지정) |
| GET | /members | 멤버 전체 조회 |
| GET | /members/{id} | 멤버 단건 조회 |
| GET | /members/{id}/assignments | 멤버별 과제 조회 |
| PUT | /members/{id} | 멤버 수정 (소속 팀 변경 포함) |
| DELETE | /members/{id} | 멤버 삭제 |

### Assignment

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /assignments | 과제 생성 (담당 멤버 지정) |
| GET | /assignments | 과제 전체 조회 |
| GET | /assignments/{id} | 과제 단건 조회 |
| PUT | /assignments/{id} | 과제 수정 |
| DELETE | /assignments/{id} | 과제 삭제 |
