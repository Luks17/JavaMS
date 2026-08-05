FROM maven:3.9.11-eclipse-temurin-21-alpine

WORKDIR /app

ARG SERVICE

COPY ${SERVICE}/pom.xml ./
RUN mvn -B -DskipTests dependency:go-offline

COPY ${SERVICE}/src ./src
RUN mvn -B -DskipTests compile

CMD ["mvn", "-B", "spring-boot:run", "-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"]
