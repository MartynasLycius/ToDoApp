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

**********

## Technical requirements (applied)
* Thymeleaf as frontend framework
* For backend used Java EE (Spring boot)
* Used PostgreSQL 12.5 database
* A Maven project

# Features!
- A Spring Boot and Thymeleaf (Template Engine) project 
- User can entry task item(s)
- User can update task item(s)
- User can track his/her task list
- Task item(s) pagination
- Pages interact between each other logically

## Getting Started
### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later
* PostgreSQL

### Clone
To get started you can simply clone this repository using git:
```
git clone https://github.com/shakhawatm/ToDoApp.git
cd ToDoApp
```

### Configuration
You have to update the following settings:
```
#PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/<database_name>?currentSchema=<schema_name>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
```

The configuration is located in `src/main/resources/application.properties`.

### Run the Project
You can run the maven application from the command line using:
```
mvn clean spring-boot:run
```

### Test Status-Posting Web Application
1. Browse the following path `http://localhost:8080`

### Snapshot of output
- https://i.ibb.co/R46rpZ6/Page-for-listing-all-the-todo-items.png
- https://i.ibb.co/JC2mKTh/Todo-item-add-form.png
- https://i.ibb.co/Z8C7jWY/Todo-item-edit-form.png
- https://i.ibb.co/Th8M15W/Back-end-Form-Validation.png
- https://i.ibb.co/KWyhzKy/Back-end-Maximum-Length-Form-Validation.png
- https://i.ibb.co/nPtSm3Y/Data-updated-with-successful-message.png