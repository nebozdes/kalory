FROM eclipse-temurin
WORKDIR /usr/src/app
COPY . .
RUN ./gradlew build
EXPOSE 8081
CMD ["java", "-jar", "build/libs/kalory-0.0.1-SNAPSHOT-plain.jar"]