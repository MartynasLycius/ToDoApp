# ToDoApp

#### Description:

In home page (http://HOST:PORT/) you will find two menu 

1. TODO List

*a.By clicking TODO List(http://HOST:PORT/todo/alltodolist)  you will able to see all todo list .
*b.upper rightmost conner there is a "add new" button to add new todo (http://HOST:PORT/todo/show)
*c.you can Edit todo by clicking edit button on the table (http://HOST:PORT/todo/edit/todoId)
*d.you can Dlete todo by clicking delete button on the table (http://HOST:PORT/todo/delete/todoId)
*e.one can search todo on the search bar.

2. USER List

*a.By clicking USER List(http://HOST:PORT/user/allUser)  you will able to see all User list .
*b.upper rightmost conner there is a "add new" button to add new user (http://HOST:PORT/user/show) user can have two role
   *2.b.1 ROLE_ADMIN
   *2.b.2 ROLE_USER
*c.you can Edit User by clicking edit button on the table (http://HOST:PORT/user/edit/todoId)
*d.you can Dlete User by clicking delete button on the table (http://HOST:PORT/user/delete/todoId) .But You can not delete "Admin USER"
*e.one can search user on the search bar.





## Backend

#### Technology used: 

- spring-boot
- spring-boot-starter-web
- spring-data-jpa
- spring-boot-starter-security
- MySQL
- Java 8
- maven



## Frontend

#### Technology used: 

- spring-boot-starter-thymeleaf
- jquery-vuex
- normalize.css
- ajax



#### Database configuration: 

*  You can find database configuration in  *application.properties* file
It looks like below:
spring.datasource.url = jdbc:mysql://localhost:3306/"YOUR_DB_NAME"?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username = "YOUR_DB_USER_NAME"
spring.datasource.password ="YOUR_DB_PASSWORD"

* When run the project It will create a amdin user for the first Time .
with below creadintial:
*USER NAME:admin
*PASSWORD :1234

you can also find a database in data folder . which named "tododb.sql"
```
ToDoApp
├── data
├── src
├── target
```


#### Installation guide:

*Build Spring Boot Project with Maven:
To be able to run your Spring Boot app you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.
 *"mvn install"

OR,You can also use Maven plugin to run your Spring Boot app. Use the below example to run your Spring Boot app with Maven plugin:
 *"mvn spring-boot:run"




