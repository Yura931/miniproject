# Spring Cloud Bus

---
> - 분산 시스템의 노드(MS)를 경량 메시지 브로커와 연결
> - 상태 및 구성에 대한 변경 사항을 연결된 노드에게 전달(Broadcast)
> - configuration server와 연결되어 있는 클라우드 버스가 마이크로 서비스한테 직접 데이터를 푸쉬 해주는 방식으로 변경사항을 알려 줌
> - 외부에서 HTTP POST /busrefresh 호출
> - 같은 공간에 연결되어진 모든 클라이언트, 모든 마이크로 서비스들한테 데이터를 주겠다는 의도

### AMQP(Advanced Message Queuing Protocol)
- 메시지 지향 미들웨어를 위한 개방형 표준 응용 계층 프로토콜
- 메시지 지향, 큐잉, 라우팅(P2P, Publisher-Subscriber), 신뢰성, 보안
- Erlang(스웨덴에서 개발, 함수형 병행성 프로그래밍 언어, 병렬 처리 특화), RabbitMQ에서 사용
- 데이터 변경 알려줌

### Kafka 프로젝트
- Apache Software Foundation이 Scalar 언어로 개발한 오픈 소스 메시지 브로커 프로젝트
- 분산형 스트리밍 플랫폼
- 대용량의 데이터를 처리 가능한 메시징 시스템


#### RabbitMQ
- 메시지 브로커
- 초당 20+ 메시지를 소비자에게 전달
- 메시지 전달 보장, 시스템 간 메시지 전달
- 브로커, 소비자 중심

#### Kafka
- 초당 100k+(10만개) 이상의 이벤트 처리
- Pub/Sub Topic에 메시지 전달
- Ack를 기다리지 않고 전달 가능
- 생산자 중심


---

## Dependencies 추가
- Config Server
  - AMQP for Spring Cloud Bus, Actuator
- config-server, gateway-service, board-service 등 마이크로 서비스 RabbitMQ 클라이언트로 등록
  - 변경 사항이 생기면 변경사항을 AMQP라는 프로토콜로 받을 수 있음
  - 웹 브라우저에서 접속할 때는 15672 포트, 시스템에서 AMQP 프로토콜 사용할 때는 5672포트 사용
- config-server에 변경사항 요청이 들어오면 RabbitMQ에 요청사항을 받았음을 통보 -> RabbitMQ에 등록되어진 또 다른 마이크로 서비스에 그 정보를 일괄적으로 푸시해주게 됨 (일종의 클라이언트 역할)