# Create Plan
This is a simple project to create a loan and list of repayment plan of the loan and there is a REST API for doing that.
It is a post method and with this address "http://localhost:8080/loan/generate-plan"

# Plan Generator
In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a
loan.
To be able to calculate a repayment plan specific input parameters are necessary:
duration (number of installments in months)
nominal rate (annual interest rate)
loan amount (principal amount)
Date of Disbursement/Payout ("startDate")

# Technical description
This project is implemented by spring and spring boot. 
In this case I tried to separate layers like service layer, repository layer and controller for doing some change better 
in the future.
Handing error is implemented by spring annotation that called controller advice.
Rest API(s) are restful designed.  

## the pre-requisites
* You have to install Java 8.
* You have to install Maven 3.

## Technologies: 
* Spring WEB (to create HTTP request and response by adding spring-boot-starter-web in POM file maven)
* Spring AOP (to write a aspect to validate input provided user y adding spring-boot-starter-aop in POM file maven)
* Swagger (to generate automatically some documentations from REST API and classes by adding springfox-swagger2 in POM file maven)
* Spring Test (to write test integration and unit test by adding spring-boot-starter-test in POM file maven)
* Spring actuator (to monitor and manage our application.When application is pushed to production, you can choose to manage and monitor your application using HTTP endpoints)  
* Map struct (to map DTO objects to main entities by adding mapstruct-jdk8 in POM file maven)
* Maven (to build and manage our application) 

### Run test methods:
```
Use "mvn clean package" to run the test case and integration test.
```

### Run in development envirenment:
To run the project with **spring-boot:run** in development environment.
```
After runing you can test and see API(s) on a browser with this URL "http://localhost:8080/swagger-ui.html".  
```

### Run in production environment:
Use java -jar file with the below command:  
**java -jar -Dspring.profiles.active=prod repayment-service-boot.jar**

## Task lists
You can use these following stack technology to make the application more enterprise:
- [ ] Spring Security
- [ ] Spring Cloud
- [ ] Spring Session
- [ ] Spring Oauth2
- [ ] API gateway like Zuul
- [ ] Circuit breaker
- [ ] Spring Cache
- [ ] Spring Data JPA

docker build -f Dockerfile -t repayment-service-boot .
docker images
