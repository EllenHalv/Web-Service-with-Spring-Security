# Web Service with Spring Security JWT

### Description

This project is a Web Service built using Spring Boot with Spring Security and JWT (JSON Web Token) for authentication. It includes functionalities for user and admin authentication and movie management using MySQL.

### Table of Contents

1. [Installation](#installation)
2. [How to Use](#how-to-use)
3. [Dependencies](#dependencies)
4. [License](#license)
5. [Badges](#badges)
6. [How to Contribute](#how-to-contribute)
7. [Tests](#tests)


### Installation
Before you can run this application, make sure you have the following software and tools installed on your system:

* Java Development Kit (JDK) 8 or later
* MySQL Database

1. Clone the repository to your computer and open it in IntelliJ.

2. Start your MySQL server. (if you don't have it installed, you can download it here: https://dev.mysql.com/downloads/mysql/)
   Then manually create a database in MySQL called "spring-security"!

3. Configure the application.properties file in the resources folder to match your MySQL settings (username/password).

4. Run the main class to start the application.

### How to use

Open Postman and use the following guide to sign up, login and handle data:

* Sign up:

POST:
```
localhost:8080/auth/signup
```

JSON body:
```
{
"firstName": "ellen",
"lastName": "halvardsson",
"email": "ellen@mail.com",
"password" : "12345"
}
```

* Login:

POST:
```
localhost:8080/auth/signin 
```

JSON body:
```
{
"username" : "ellen",
"password" : "12345"
}
```

* Add a movie:

POST:
```
localhost:8080/movies
```

JSON body:
```
{
"title" : "movieTitle",
"year" : 0000
}
```

* Get one movie:

GET:
```
localhost:8080/movies/{id}
```

* Get all movies:

GET:
```
localhost:8080/movies
```

* Update a movie:

PUT:
```
localhost:8080/movies
```

JSON body:
```
{
"title" : "newTitle",
"year" : 0000
}
```
* Delete a movie:

DELETE:
```
localhost:8080/movies/{id}
```

### Dependencies

* [spring boot starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.2.0)
* [spring boot starter security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.2.0)
* [spring boot starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
* [mysql connector](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
* [org apache commons lang3](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.14.0)
* [lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30)
* [json web token](https://mvnrepository.com/artifact/org.openidentityplatform.commons/json-web-token/2.1.1)

### License
This project is licensed under the MIT License - for more information please follow this link [MIT License](https://choosealicense.com/licenses/mit/).

### Badges

[![made-with-java](https://img.shields.io/badge/Made%20with-Java-1f425f.svg)](https://www.java.com)
[![Generic badge](https://img.shields.io/badge/Made%20with-SpringBoot-1f425f.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/Made%20with-MySQL-1f425f.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/Made%20with-Maven-1f425f.svg)](https://shields.io/)

### How to Contribute
Contributions and suggestions are welcome! To report bugs, make suggestions or contribute to the code refer to the instructions below:

1. Fork this repository
2. Create your own branch from main
3. Add your contributions (code or documentation)
4. Commit and push
5. Wait for pull request to be merged

Alternatively, if you find a bug or have a suggestion, you can open an issue.

## Tests


