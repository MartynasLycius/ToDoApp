# To Do App Installation Instruction

Step 1: Go to the root folder of the project.
Step 2: Launch command prompt.
Step 3: Execute following command: 

		mvn clean install

Step 4: Wait for few seconds till the build finishes with 'BUILD SUCCESS' message.

# RUN To Do App 

Step 1: Launch command prompt same way mentioned in the installation process if not already open
Step 2: Execute following command:

		mvn spring-boot:run
		
Step 3: Application should be running in port 8989. Launch app by navigating to following path in the browser:

		http://localhost:8989/todo-tracker-app/taskDashBoard
		
		
# Declaration:

Have used following tools and frameworks to build the app:

Frontend: Vaading 14.4.2
Backend: Spring-boot, JPA
JDK Version: 1.8
Database: derby (in memory database to maintain simplicity)