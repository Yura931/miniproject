FROM openjdk:17-alpine
VOLUME /tmp
COPY build/libs/gateway-service-1.0.jar GatewayService.jar
ENV DOCKERIZE_VERSION v0.7.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz
ENTRYPOINT ["dockerize", "-wait", "http://config-service:8888", \
"-timeout", "20s", \
"java","-jar","GatewayService.jar"]