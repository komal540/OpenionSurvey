# JDK Image base
FROM amazoncorretto:17-alpine-jdk

# (optional) Maintainer
MAINTAINER kinandcarta.com

# Create a linux USER for context
#RUN addgroup -S spring && adduser -S springuser -G spring
#USER springuser:spring

VOLUME /tmp

ARG JAR_FILE=build/docker/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]