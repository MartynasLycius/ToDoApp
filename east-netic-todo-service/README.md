1. Create a database named "interview" if it is not already exists
2. Then create "todo" schema if it is not already exists 

5.  Run following DDL to create "todo" table

    CREATE TABLE todo.todo (
        id uuid NOT NULL,
        user_id uuid NOT NULL,
        title varchar(512) NOT NULL,
        description varchar(1028) NOT NULL,
        "date" timestamp NOT NULL,
        CONSTRAINT todo_pkey PRIMARY KEY (id)
    );

6. Run mvn clean spring-boot:run

7. To get swagger documentation visit following link
http://localhost:8082/swagger-ui.html


# Test code settings start here
1. Create a database named "interview-test" if it is not already exists
2. Then create "todo" schema if it is not already exists 

3.  Run following DDL to create "todo" table

    CREATE TABLE todo.todo (
        id uuid NOT NULL,
        user_id uuid NOT NULL,
        title varchar(512) NOT NULL,
        description varchar(1028) NOT NULL,
        "date" timestamp NOT NULL,
        CONSTRAINT todo_pkey PRIMARY KEY (id)
    );

4.
   
INSERT INTO todo.todo (id,user_id,title,description,"date") VALUES
	 ('f6153439-5c22-4821-992c-ef026c023d52','a45d7a3a-5ab8-4b6f-a6ce-502ea39dfc73','Travel','Have to go Gazipur','2021-01-29 06:00:00.000');	 