FROM maven:3.8.1-jdk-8 AS build

COPY /src /ExpenseTracker/src
COPY pom.xml /ExpenseTracker/
RUN mvn -f /ExpenseTracker/pom.xml clean package -DskipTests

FROM java:8
COPY --from=build /ExpenseTracker/target/ExpenseTracker-0.0.1-SNAPSHOT.jar /usr/local/lib/ExpenseTracker.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","/usr/local/lib/ExpenseTracker.jar"]


