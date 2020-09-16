# eshopping-springboot-project

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


## Requirements

For building and running the application you need:

- [JDK 1.11](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.eshopping.io.SpringBootEshoppingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## TODO: Deploying the application 

## DATABASE
In this used in-built h2 database. In case if you want to use another database, add a dependency in pom.xml and add database specific properties in `application.properties` files such as driver name, username and password.

## Security
Used JWT authentication and authorization.

## Access the project
     ## From Postman
     For Register and login
      1. http://localhost:8080/eshopping/users/register
      2. http://localhost:8080/eshopping/users/login - with user name and password
      Please check the code for other end points
      
      
      ## From Angular project, we can directly access pages so no need of end points to note.
