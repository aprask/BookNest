FROM gradle:7.3.3-jdk17 as builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN chmod +x ./gradlew
COPY src src
RUN ./gradlew clean build -x test --stacktrace

FROM azul/zulu-openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]