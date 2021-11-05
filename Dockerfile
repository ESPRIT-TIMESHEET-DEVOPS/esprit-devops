#FROM maven:latest as mvn
#COPY ./ ./app
#WORKDIR /app
#COPY settings.xml /usr/share/maven/ref/
#RUN mvn -B -f /app/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
#RUN mvn dependency:go-offline -B
#RUN mvn package && cp /app/target/timesheet-*.jar app.jar
##COPY --from=maven /root/.m2 /root/.m2
##COPY --from=maven /app/app.jar ./app.jar
#
##RUN mvn clean install
##CMD mvn spring-boot:run
#EXPOSE 9090
#CMD ["java", "-jar","timesheet.jar"]
#FROM openjdk:latest
FROM openjdk:18-alpine
FROM maven:latest
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never
COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests
#ADD ./target/timesheet-1.0.jar /deploy/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/timesheet-1.0.jar"]
