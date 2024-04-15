FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/account-management-1.0.0.jar /app/account-management-1.0.0.jar

EXPOSE 8080

CMD ["java", "-jar", "account-management-1.0.0.jar"]