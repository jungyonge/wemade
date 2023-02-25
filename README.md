## 클레이튼 트랜잭션 조회 API

--- 
## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA
    - OS: Mac
    - Git
- Server
    - Java11
    - Spring Boot 2.7.8
    - Spring Security
    - JWT
    - JPA
    - Gradle
    - H2

---
## 코드 구조
- DDD, 레이어드 아키텍쳐를 지향하는 패키지구조
- Spring Security 사용하여 인증, 인가 시스템 구축
- 정상적으로 로그인이 되었다면 JWT Access Token, Refresh Token 발급
- 인증과 관련된 API를 제외한 나머지는 Header에 JWT 토큰필요
---
## 빌드, 실행
~~~

//빌드
./gradlew clean build

//디렉토리이동
cd build/libs

//실행
java -jar wemade-0.0.1-SNAPSHOT.jar

//swagger 접속
http://localhost:8080/swagger-ui/index.html

//h2-console 접속 
http://localhost:8080/h2-console/login.jsp
~~~

---
## 테스트 순서
1. http://localhost:8080/swagger-ui/index.html# 페이지이동합니다
2. User API에서 회원가입 요청으로 회원가입합니다.
3. Authorization API에서 로그인 요청으로 로그인합니다
4. 로그인이 정상적으로 되었다면 access token이 발급됩니다
   - swagger 상단오른쪽에 authorize 버튼은 클릭하여 Bearer {access token}을 입력합니다.
5. TX API에서 조회실행
   - 0x9487ef8e0baed40ba21ee3a077904549bd14ceee87f7c835e4abdbf6782042db

---

