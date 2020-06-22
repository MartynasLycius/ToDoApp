# sb-boiler-plate-backend

health URL: http://localhost:8080/sb-boiler-plate-backend/actuator/health

# Configure Database

Please set below Environment Variables to run the application:

```bash
DB_HOSTNAME
DB_NAME
DB_USERNAME
DB_PASSWORD
```

if you use eclipse/intellij you can add the environment variable from tomcat configuration page

If you are unable to set environment variable please set the database configuration in application.yml file

# Default admin login

```bash
username: admin
password: password
```

# AngularBoilerPlateApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.0.1.

## Development server

Run `npm install` in project root folder

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Also you can change `baseUrl: "http://localhost:8080/sb-boiler-plate-backend"` in `environment.ts` file if you are running backend somewhere else.
