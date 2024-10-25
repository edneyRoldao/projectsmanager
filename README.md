# Project Management Web Application

A Java-based web application for managing projects. This app allows users to create, read, update, and delete projects.


## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Maven** - Dependency management and build automation
- **Flyway** - Database migration management
- **JSP** - View layer for rendering UI
- **PostgreSQL** - Database
- **JUnit** - Testing framework


## Features

- **CRUD Operations** for project management
- **Database Migrations** managed with Flyway
- **Web Interface** using JSP
- **Testing** with JUnit


## Prerequisites

- **Java 17** or higher
- **PostgreSQL** - Ensure PostgreSQL is installed and running.
- **Maven** - For dependency management and build tasks.


## Getting Started

### Database Setup

1. install postgres with docker

``` 
docker run -d \
  --name bd-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -v $(pwd)/../data/postgres:/var/lib/postgresql/data \
  postgres:15.8-alpine
```

2. Create  PostgreSQL database:

```
CREATE DATABASE projects_manager;
```

3 Update your PostgreSQL credentials in application.properties:

```
spring.datasource.url
spring.datasource.username
spring.datasource.password
 ```

### Project Setup

1. Clone the repository:

```
git clone https://github.com/edneyRoldao/projectsmanager.git
```

2. Navigate to the project directory:

```
cd project-management-app
```
    
3. Install dependencies and build the project:

```
mvn clean install
```
       
4. Run database migrations with Flyway:

```
mvn flyway:migrate
```
    
5. Start the application:

```
mvn spring-boot:run
```

## WebApp services routes 
The default port is 8080. you can change this with the following property: server.port at 
application.properties

1. project list page

```
http://localhost:8080/projects
```

2. project detail

```
http://localhost:8080/projects/project_id
```

3. insert project

```
http://localhost:8080/projects/create
```

3. update project

```
http://localhost:8080/projects/update/project_id
```


## Running Tests

To execute unit tests, run:

```
mvn clean test
```


## Project API documentation

- create member curl:

```
curl --location 'http://localhost:8080/members/api/create' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Edney Roldao",
    "employee": true,
    "assignment": "desenvolvedor fullstack senior",
    "document": "64836333008"
}'
```

- get members curl:

```
curl --location 'http://localhost:8080/members/api/all'
```

- get project statuses

```
curl --location 'http://localhost:8080/projects/api/statuses'
```

- get project risks

```
curl --location 'http://localhost:8080/projects/api/risks'
```
