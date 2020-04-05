# ToDoApp

####Application Objectives:

1. Page for listing all the todo items (http://HOST:PORT/)
2. Todo item add  forms (http://HOST:PORT/add-item)
3. ToDo item edit forms (http://HOST:PORT/update-item)


####Description:
HOME page lists all the ToDo items, it has navigation menu to reach ToDo add page and each ToDo listing as two icon in rightmost column. One to reach the edit page another to delete the ToDo item. 


### Folder Structure 
```
ToDoApp
├── backend
├── frontend
├── .gitignore
├── INSTALLATION.md
└── README.MD

```

##Backend

####Technology used: 

- spring-boot
- springfox-swagger2
- commons-beanutils
- lombok
- spring-data-jpa
- MySQL
- Java 8

####Folder Structure: 
```
backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── rali
│   │   │           ├── config
│   │   │           ├── constant
│   │   │           ├── controller
│   │   │           ├── dto
│   │   │           │   └── transformer
│   │   │           ├── entity
│   │   │           ├── exception
│   │   │           │   └── handler
│   │   │           ├── repository
│   │   │           ├── service
│   │   │           └── Application.java
│   │   └── resources
│   │       ├── application.yml
│   │       └── data.sql
│   └── test
├── target          
└── pom.xml
```

####Installation: 
 * Modify the database properties of *application.yml* with your designated database information 
 * Modify **spring.jpa.hibernate.dl-auto** of *application.yml* to (**create/update/create-drop** ) accordingly to automate the table creation
 * Modify **server.port** of *application.yml* with your designated port number 
 * Open the terminal, traverse to *ToDoApp/backend/* directory and execute this command **mvn spring-boot:run**
 * Go to the link *http://YOUR_HOST:PORT/api/swagger-ui.html*
 * (Optional) There are some sample data in **data.sql** file under *resource* package, you prepopulate some data before starting the frontend app
 
##Frontend

####Technology used: 

- vue
- vuetify
- vuex
- vue-router
- axios

####Description: 
There are three view components 
 - AddTodoItem.vue (Renders new ToDo add form)
 - TodoItemList.vue (Renders ToDo listings)
 - UpdateTodoItem.vue (Renders existing ToDo update form)
 

####Folder Structure: 
```
frontend
├── public 
│   ├── favicon.icon
│   └── index.html
├── src
│   │── assets
│   │   ├── favicon.icon
│   │   └── index.html
│   ├── components
│   │   └── Navbar.vue
│   ├── plugins
│   │   └── vuetify.js
│   ├── resource
│   │   └── properties.js
│   ├── router
│   │   └── index.js
│   ├── service
│   │   └── TodoService.js
│   ├── store
│   │   └── storage.js
│   └── views
│       ├── AddTodoItem.vue
│       ├── TodoItemList.vue
│       └── UpdateTodoItem.vue
├── App.vue 
├── main.js
├── .env
├── bable.config.js
├── package.json
└── vue.config.js

```

####Installation: 
 * Modify the **properties.js** file under *frontend/src/resource* directory with backend api related information 
 * Modify port number the **.env** file under *frontend* directory with your designated port (default port is 3000)
 * Open the terminal, traverse to *ToDoApp/frontend/* directory and execute the commands **npm install** and **npm run serve**
 * Open your browser and to to the link *http://YOUR_HOST:PORT*
