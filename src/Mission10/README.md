# Mission10 - 예외 처리 & FE 연동

작성자: 김성관

## 과제 목표

- `@RestControllerAdvice`로 전역 예외 처리를 구현한다
- 커스텀 예외와 통일된 에러 응답 형식(`ErrorResponse`)을 정의한다
- 프론트엔드에서 API를 호출하여 CRUD가 브라우저에서 동작하도록 연동한다

## 도메인 구조

Mission09의 `Team(1) - Member(N) - Assignment(N)` 연관관계를 그대로 사용하며,
Service 계층이 `Optional`/`boolean`을 반환하던 방식에서 예외를 던지는 방식으로 리팩토링되었다.

## 예외 처리

- `BusinessException`: HTTP 상태 코드와 에러 코드를 갖는 커스텀 예외의 부모 클래스
  - `TeamNotFoundException` (404, `TEAM_NOT_FOUND`)
  - `MemberNotFoundException` (404, `MEMBER_NOT_FOUND`)
  - `AssignmentNotFoundException` (404, `ASSIGNMENT_NOT_FOUND`)
- `GlobalExceptionHandler` (`@RestControllerAdvice`)가 아래 예외를 통일된 `ErrorResponse` 형식으로 변환한다.
  - `BusinessException` -> 각 예외에 정의된 상태 코드
  - `MethodArgumentNotValidException` (`@Valid` 검증 실패) -> 400
  - `IllegalArgumentException` -> 400
  - 그 외 `Exception` -> 500

```json
{
  "timestamp": "2026-07-07T12:00:00",
  "status": 404,
  "code": "MEMBER_NOT_FOUND",
  "message": "존재하지 않는 멤버입니다. id=99"
}
```

## 실행 방법

```
gradle bootRun
```

H2 인메모리 데이터베이스를 사용하며, 애플리케이션 실행 시 스키마가 자동 생성된다.
서버 실행 후 브라우저에서 `http://localhost:8080` 에 접속하면 팀/멤버 CRUD와 검색을 수행할 수 있는 프론트엔드 화면이 열린다.

## API

### Team

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /teams | 팀 생성 |
| GET | /teams | 팀 전체 조회 |
| GET | /teams/search?name= | 팀 이름 검색 |
| GET | /teams/{id} | 팀 단건 조회 |
| GET | /teams/{id}/members | 팀별 멤버 조회 |
| PUT | /teams/{id} | 팀 수정 |
| DELETE | /teams/{id} | 팀 삭제 |

### Member

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /members | 멤버 생성 (소속 팀 지정) |
| GET | /members | 멤버 전체 조회 |
| GET | /members/search?keyword= | 이름/파트로 멤버 검색 |
| GET | /members/{id} | 멤버 단건 조회 |
| GET | /members/{id}/assignments | 멤버별 과제 조회 |
| PUT | /members/{id} | 멤버 수정 (소속 팀 변경 포함) |
| DELETE | /members/{id} | 멤버 삭제 |

### Assignment

| Method | URL | 설명 |
| --- | --- | --- |
| POST | /assignments | 과제 생성 (담당 멤버 지정) |
| GET | /assignments | 과제 전체 조회 |
| GET | /assignments/search?title= | 제목으로 과제 검색 |
| GET | /assignments/{id} | 과제 단건 조회 |
| PUT | /assignments/{id} | 과제 수정 |
| DELETE | /assignments/{id} | 과제 삭제 |

## 프론트엔드

`src/main/resources/static`에 위치한 정적 페이지(`index.html`/`app.js`/`style.css`)가 `fetch()`로 위 API를 호출한다.

- 팀/멤버 생성, 수정, 삭제, 검색이 모두 화면에서 동작한다.
- 응답이 실패하면 백엔드가 내려주는 `ErrorResponse`(`status`, `code`, `message`)를 그대로 화면 하단 로그에 표시하여
  전역 예외 처리 결과를 프론트엔드에서도 확인할 수 있다.
