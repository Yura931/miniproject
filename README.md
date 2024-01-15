# 개인 프로젝트

---

> - 하나의 서버만 사용해 하나의 IP 어드레스에서 여러 포트 사용하는 방식으로 구성
>   - 게시판, 게시물 관리의 경우 사용자와 관리자가 사용하는 api가 비슷한데 서버를 따로 두어야 하나 고민 도중 서비스(도메인) 단위로 구축하면 좋을 것 같다는 생각을 함
>   - 완전한 MSA는 아니며 현재는 인증, 유저, 게시판, 게시물 서비스만 있는 형태로 크지 않지만 개인 프로젝트로서 기능과 서비스를 추가하기 더 편리 할 것이라 판단  


### Spring Cloud
- 전통적인 모노리스 방식이 아닌 독립적으로 개발하기 위한 서비스, 마이크로 서비스 아키텍처를 지원하기 위한 프레임워크
- 분산 시스템에 빠르게 어플리케이션을 개발하는 데 목적을 두고 만들어짐

#### 구성
- Centralized configuration management
  - Spring Cloud Config Server
    - 환경 설정 관리
    - 다양한 마이크로 서비스에서 사용할 수 있는 어떠한 정보를 클라우드 컨피그라는 서버를 통해 외부 저장소에 환경설정 정보를 둘 수 있음
    - 게이트웨이 IP, 서버가 가지고 있어야 하는 정보(토큰 등)
    - 마이크로 서비스를 다시 빌드하고 재배포하는 것이 아닌 외부 저장소에 있는 자료만 변경하게 되면 다 적용 
- Location transparency
  - Naming Server (Eureka)
    - 서비스 등록과 위치정보 확인, 검색 등 서비스
- Load Distribution (Load Balancing)
  - Spring Cloud Gateway
    - 서버에 들어왔던 요청 정보를 분산하기 위한 용도
    - 로드 밸런싱, 게이트웨이 기능
- Easier REST Clients
  - Feign Client
    - 각각의 마이크로 서비스 간의 통신

---
**강의 통해 보충 할 예정**
- Visibility and monitoring
  - Zipkin Distributed Tracing
  - Netflix API gateway
    - 시각화와 모니터링을 위해서 분산 추적을 위한 집킨, 외부 모니터링 서비스
    - 로그 추적
- Fault Tolerance
  - Netflix Hystrix
    - 장애가 발생했을 경우 빠르게 복구하기 위한 회복성 패턴
  - Resilience4j

---

## Spring Cloud Netflix Eureka
- 마이크로 서비스들을 등록
- 서비스 디스커버리 역할
  - 외부에서 다른 어떤 서비스들이 마이크로 서비스를 검색하기 위해 사용되는 개념
- 각각의 마이크로 서비스가 자신의 위치 정보를 스프링 클라우드 넷플릭스 유레카 서버에 등록
- Eureka Dashboard를 통해 등록되어 있는 인스턴스들을 확인할 수 있다.

## API Gateway Service
- 사용자나 외부 시스템으로부터 요청을 단일화하여 처리
- 사용자가 설정한 라우팅 설정에 따라서 각각 엔드포인트로 클라이언트를 대신해서 요청하고 응답을 받으면 다시 클라이언트한테 전달해주는 프록시 역할
- 시스템 내부 구조는 숨기고 외부의 요청에 대해서 적절한 형태로 가공해서 응답

## Feign Client
- Spring Cloud에서의 MSA간 통신
- 직접적인 서버의 주소나 마이크로 서버의 주소 포트번호 없이 마이크로 서비스 이름으로 호출 가능