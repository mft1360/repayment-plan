FROM openjdk:8
ADD target/repayment-service-boot.jar repayment-service-boot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","repayment-service-boot.jar"]
