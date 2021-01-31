1. Create a database named "interview" if it is not already exists
2. Then create "user_management" schema if it is not already exists 
3. -> Run following DDL to create user table

    CREATE TABLE user_management."user" (
    	id uuid NOT NULL,
    	"password" varchar(255) NULL,
    	full_name varchar(255) NULL,
    	user_name varchar(255) NULL,
    	CONSTRAINT user_pkey PRIMARY KEY (id)
    );

4. Run mvn clean spring-boot:run

5. To get access token and refresh token use below curl

curl -X POST http://east-netic:secret@localhost:8081/oauth/token  -H 'Content-Type: application/x-www-form-urlencoded' -d 'grant_type=password&username=hafiz&password=12345'

6. To validate access token use below curl

curl -X POST http://localhost:8081/oauth/check_token/?token=<access_token>

# Test code settings start here 

1. Create a database named "interview-test" if it is not already exists
2. Then create "user_management" schema if it is not already exists 
3. -> Run following DDL to create user table

    CREATE TABLE user_management."user" (
        id uuid NOT NULL,
        "password" varchar(255) NULL,
        full_name varchar(255) NULL,
        user_name varchar(255) NULL,
        CONSTRAINT user_pkey PRIMARY KEY (id)
    );
    
4. Run following query    
INSERT INTO user_management."user" (id,"password",full_name,user_name) VALUES
	 ('a45d7a3a-5ab8-4b6f-a6ce-502ea39dfc73','$2a$10$KK1rhevCJ8zOav26i.R8GOq8yQigdMae/qlLfjd4TLrBQHifyBDoW','Hafiz','hafiz');

5. Run following command to run test code mvn clean test    