# ToDoApp

ToDoApp : Manage your all todo in a single place.

## User
* Inmemory user added
* User name : admin. Password: admin
* User can log in and access the application

## Features
You need to make a Todo app with these requirements:
1. User can see the dashboard and get all ToDo list 
2. New todo item can be added in a standalone page
3. User can edit/update existing task 
4. User can delete existing task
5. User can visualize single todo as a summary page
9. Navigation added logically. (User can go from list to new or edit and when saving go back to list)

## Technical 
* Language: Java 11
* Framework for backend and rest endpoint : Spring Boot
* Framework for DAO : Spring Data JPA
* Lomobk used to reduce code footprint
* log4j added for logging
* Database : MySQl
* Build Environment:Maven
* Server: Inbuilt Tomcat
* Front End : Thymeleaf as Template Engine,BottStrap for styling

## Main points
* Structure your code : Yes
* Use best practises : Yes
* Use naming conventions : yes
* Show understanding of software development concepts : yes

## Improvement Scope :
Improvement scope is  there, however those are out the assignment scope.

## Instruction to run :
Before running this application you should have to confirm following:
* JDK 11 installed. <pre>java --version</pre>
* maven installed.  <pre>mvn --version</pre>
* mysql installed.
* Migration script added if required <pre>/resources/db/migration/V1__InitialTables.sql</pre>

Then run the following command on terminal:
* Application will start on 50001 port. (http://localhost:5001/app)
<pre>
    git clone https://github.com/asraful/ToDoApp.git  [change as you needed]
    cd workspace
    mvn spring-boot:run
</pre>

* Successful run will be appeared in terminal as: <pre>2022-05-26 04:36:23.123  INFO 25236 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 5001 (http) with context path '/app'
2022-05-26 04:36:23.133  INFO 25236 --- [  restartedMain] com.todo.app.AppApplication              : Started AppApplication in 3.127 seconds (JVM running for 4.178)
</pre>



