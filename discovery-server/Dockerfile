FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/*.jar DiscoveryServer.jar
ENTRYPOINT ["java","-jar","DiscoveryServer.jar"]