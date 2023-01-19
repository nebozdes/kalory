FROM gradle
WORKDIR /usr/src/app
COPY . .
# We're excluding tests during build stage to avoid the need to provide docker-in-docker functionality
# Of course test goal should be run in before during the full CI/CD pipeline
RUN ./gradlew bootJar -x test
EXPOSE 8080
CMD ["java", "-jar", "build/libs/kalory-0.0.1-SNAPSHOT.jar"]