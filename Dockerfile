FROM openjdk:23
LABEL authors="seancravener"
WORKDIR /app
COPY target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]