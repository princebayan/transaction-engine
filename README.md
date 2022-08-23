# Transaction-Engine

[![Build Status](https://github.com/princebayan/transaction-engine/actions/workflows/transaction-engine-pipeline.yaml/badge.svg)](https://github.com/princebayan/transaction-engine)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Transaction engine is a spring boot java application that expose APIs to execute a transaction based on different criteria 
such as the source account, destination account , amount ... etc. 


Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.


## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle 7.5](https://services.gradle.org/distributions/gradle-7.5-bin.zip)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.transactionengine.Application` class from your IDE.

<h3>It is preferred to run the application with a spring profile property:</h3> 

<h4>JVM System Parameter</h4>

```shell
-Dspring.profiles.active=dev
```
<h4>Environment Variable</h4>

```shell
export spring_profiles_active=dev
```
<h3>Swagger Documentation</h3>
once the application is up you can access the swagger documentation page from the 
below URL:
<br>

[Localhost Swagger](http://localhost:8082/swagger-ui.html)
<br>
<br>
Also, you can check the swagger documentation from the Cloud Server instance
<br>

[Cloud Swagger](http://137.184.12.5:8081/swagger-ui.html)


## Technologies Used

- [Spring Boot Framework](http://projects.spring.io/spring-boot/)
- [Spring Boot H2 Database](https://www.baeldung.com/spring-boot-h2-database)
- [Project Lombok](https://projectlombok.org/)
- [Allure Report](https://qameta.io/allure-report/)
- [Rest Exception Handling](https://www.baeldung.com/exception-handling-for-rest-with-spring)


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/princebayan/transaction-engine/blob/develop/LICENSE.txt) file.
