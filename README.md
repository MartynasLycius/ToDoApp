# ToDoApp
## Requirements

For building and running the application you need:
- Java Version 11
- Maven 
- Database: Postgres 
## Setup Database Settings
   - Create a database name: demodb
   - username: demouser
   - password: demoPass
## Note: Database Credentials can be changed in application.properties file
## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
