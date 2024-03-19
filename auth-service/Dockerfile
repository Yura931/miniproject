FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/auth-service-1.0.jar AuthService.jar
# dockrize 다운로드 받은 파일 카피해서 사용
#ENV DOCKERIZE_VERSION v0.7.0
#RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
#    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz
