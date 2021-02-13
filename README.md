# ToDoApp
## Required environment to run this project 
&#8594; OS must have installed java 8 or higher <br />
&#8594; OS must have installed Postgres Date base <br />
&#8594; OS must have installed Nodejs

## Framework use to complete this task:
* Spring-boot to develop back end
* VueJs to develop front end

##To run this project follow the below instructions:
1. Open PgAdmin or any Database IDE 
2. Create a Database name: EastNetic
3. Then create todo schema under EastNetic database
4. Change database credential at application.properties file according to your environment 
5. Make sure 3000 port not occupied by any other application.

6. Open terminal and go to back-end folder of this project
7. Now execute the following command: <br />
&#8594; ./mvnw clean && ./mvnw install && ./mvnw spring-boot:run 

8. Open terminal and go to front-end folder of this project
9. Make sure 8080 port not occupied by any other application.
10. Now execute the following command: <br />
npm install <br />
npm run serve <br />


#####To explore front-end open the following link in browser
http://localhost:8080/
