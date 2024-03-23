FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:17
EXPOSE 8080:8080
ENV DOCKER_PORT=8080
ENV HOST=127.0.0.1
ENV DATABASE_DRIVER=com.mysql.cj.jdbc.Driver

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/vehicles.jar
ENTRYPOINT ["java","-jar","/app/vehicles.jar"]