# ToDoApp

This is the job interview task for software developer position

## What to do
* Fork the repository
* After work is done, make a pull request and notify me by email

## Task description
You need to make a Todo app with these requirements:
1. Page for listing all the todo items
2. Todo item add and edit forms (separate pages)
3. Todo item consists of (date, item name and description)
4. Pages must interact between each other logically. (You can go from list to new or edit and when saving go back to list)

All the other specific requirements are up to you

## Technical requirements
* Use any frontend framework but Vaadin (https://vaadin.com/) is strongly recommended and would be a huge benefit
* For backend use Java EE
* Use any database (Postgres, Oracle, etc.)
* Make a Maven project

## Main points
* Structure your code
* Use best practises
* Use naming conventions
* Show understanding of software development concepts

# Project Run Instruction

## Technologies Used
* Java 8 Runtime
* Spring Boot (v2.3.5.RELEASE)
* Flyway (DB Migration)
* Thymeleaf for front end

## DB Migration
* Create a postgres schema named **todo_app**

    ```create schema todo_app;```
    
* Change the **application.properties** accordingly to connect your DB

## Run Application 

    mvn spring-boot:run

* The application will start on `port:8080`. `/` will serve the list page. 
  


