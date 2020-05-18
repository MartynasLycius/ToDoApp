# Todo Application

### Technologies:

- Java8
- Spring-boot
- H2 Database
- Maven


### Database Setup:
- No configuration change required as here I have used H2 file based database; if required we can change Database (e.g. mysql) from  `application.properties` file

### Maven Run Command:

- run without test

    `
    mvn clean spring-boot:run
    `
- run without test
	
	   `
	   mvn clean test spring-boot:run
	   `
    
- run test only

    `
    mvn clean test
    `