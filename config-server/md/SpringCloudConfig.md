# Spring Cloud Config

---

- 분산 시스템에서 서버, 클라이언트 구성에 필요한 설정 정보(application.yml)를 외부 시스템에서 관리
- 하나의 중앙화 된 저장소에서 구성요소 관리 가능
- 각 서비스를 다시 빌드하지 않고, 바로 적용 가능
- 애플리케이션 배포 파이프라인을 통해 DEV-UAT-PROD 환경에 맞는 구성 정보 사용

---

- Private Git Repository
- Secure Vault
- Secure File Storage

---

### Dependencies
- spring-cloud-starter-config
- spring-cloud-starter-bootstrap
  - or) application.yml 파일에 spring.cloud.bootstrap.enabled=true 추가

### bootstrap.yml
- application.yml 파일보다 우선순위가 높은 설정파일

### Changed configuration values
- 서버 재기동
- Actuator refresh 
  - Spring Boot Actuator
    - Application 상태, 모니터링
    - Metric(지표, 수치) 수집을 위한 Http End point 제공
- Spring cloud bus 사용