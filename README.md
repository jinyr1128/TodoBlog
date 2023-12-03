# ToDo Blog

## 프로젝트 개요
ToDo Blog는 사용자가 할 일 목록을 작성하고 관리할 수 있는 웹 애플리케이션입니다. 사용자는 할 일 카드를 생성, 조회, 수정, 삭제할 수 있으며, 각 할 일 카드에 댓글을 달 수 있습니다.

## 기능
- 회원가입 및 로그인 (OAuth)
- JWT 기반 인증
- 할 일 목록 작성, 조회, 수정, 삭제
- 댓글 작성, 수정, 삭제

## 기술 스택
- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) JAR 17
-  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) 
- Spring Boot 3.1.5
- Spring Security
- JPA/Hibernate
- ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white) 8.4
- ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
- ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
- OAuth 2.0
## 설치 및 실행 방법
### 필요 사항
- Java 11 이상
- Gradle
- MySQL

### 실행 방법
1. 프로젝트 클론
2. 각자의 웹 어플리케이션을 로컬로 실행

## API 명세서

| 엔드포인트        | 메서드 | 설명        |
|------------------|--------|------------|
| /user            | POST   | 회원가입    |
| /login           | POST   | 로그인      |
| /logout          | GET    | 로그아웃    |

## 할 일 관련 API

| 엔드포인트         | 메서드 | 설명                |
|-------------------|--------|---------------------|
| /api/articles     | POST   | 할 일 작성          |
| /api/articles     | GET    | 할 일 목록 조회     |
| /api/articles/{id}| GET    | 할 일 상세 조회     |
| /api/articles/{id}| PUT    | 할 일 수정          |
| /api/articles/{id}| DELETE | 할 일 삭제          |

## 댓글 관련 API

| 엔드포인트         | 메서드 | 설명        |
|-------------------|--------|------------|
| /api/comments     | POST   | 댓글 작성   |
| /api/comments/{id}| PUT    | 댓글 수정   |
| /api/comments/{id}| DELETE | 댓글 삭제   |


## ERD
![스크린샷 2023-11-20 오전 11.01.43.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-20%20%EC%98%A4%EC%A0%84%2011.01.43.png)
## 디렉토리 구조
![스크린샷 2023-11-20 오전 11.08.52.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-11-20%20%EC%98%A4%EC%A0%84%2011.08.52.png)
## 다이어그램
![spring_boot_structure_diagram.png](img%2Fspring_boot_structure_diagram.png)
## 테스트 코드
![스크린샷 2023-12-04 오전 12.30.01.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-12-04%20%EC%98%A4%EC%A0%84%2012.30.01.png)
## 프로젝트 회고
이 프로젝트는 자바 숙련주간의 개인 과제로 시작되었습니다. 이 기간 동안, 저는 자바와 스프링에 대한 심층적인 이해를 목표로 삼았고, 실제로 적용할 수 있는 프로젝트를 찾고 있었습니다. 그렇게 시작된 것이 바로 이 todo블로그 프로젝트였습니다.

<br>
프로젝트를 진행하면서 가장 큰 도전은 JWT의 이해와 적용이었습니다. 이전까지는 JWT의 개념과 구현이 다소 난해하게 느껴졌습니다. 하지만 이 프로젝트를 통해 JWT를 실제로 구현하고 적용하는 과정을 경험하면서, 그 복잡함을 해결하려고 노력했습니다.

<br>
이 프로젝트를 통해 스프링에 대한 전반적인 지식이 크게 향상되었습니다. 특히, REST API의 설계부터 데이터베이스 연동, 보안 적용까지 다양한 기술적 측면을 경험하고 이해할 수 있었습니다. 또한, 어려운 문제들에 직면했을 때, 이를 헤쳐 나가는 과정에서 문제 해결 능력이 크게 성장했습니다.
<br>
이 프로젝트의 가장 큰 특징은 실제 서비스로 쉽게 전환될 수 있도록 준비된 todo블로그를 구축했다는 점입니다. 프로젝트는 단순한 과제를 넘어서 실제 사용자가 사용할 수 있는 수준까지 개발되었으며, 필요한 기능들이 포괄적으로 구현되어 있습니다. 조금의 추가 작업과 개선을 거친다면, 이 프로젝트는 실제 서비스로서의 가능성을 가지고 있습니다.
이 프로젝트는 단순한 과제를 넘어서, 제 개발 능력의 성장과 스프링에 대한 깊은 이해를 가져다주었습니다. 이 경험은 제 향후 개발 여정에 큰 자산이 될 것입니다.