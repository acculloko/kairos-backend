# Kairos Backend

## Documentation link:

https://documenter.getpostman.com/view/36203602/2sAYkBrfwu

## Installation guide
### Requirements:
Maven v3.9.9\
Java JDK (Version 21 or higher)\
MySQL Server

### Clone the repository:
```
git clone https://github.com/acculloko/kairos-backend.git
```

### Modify application.properties:
```
spring.datasource.url=jdbc:<YOUR MYSQL DATABASE URL>?serverTimezone=America/Sao_Paulo
spring.datasource.username=<YOUR MYSQL USERNAME>
spring.datasource.password=<YOUR MYSQL PASSWORD>
```
*Remove angle brackets <>

### Build and run the application:
```
mvn spring-boot:run
```
