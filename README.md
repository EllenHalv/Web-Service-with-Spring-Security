# Movie Service with Spring Security JWT

### Description

### Table of Contents (Optional)

### Installation
Before you can run this application, make sure you have the following software and tools installed on your system:

* Java Development Kit (JDK) 8 or later
* Apache Kafka
* MySQL Database

1. Clone the repository to your computer and open it in IntelliJ.

2. Start your MySQL server. (if you don't have it installed, you can download it here: https://dev.mysql.com/downloads/mysql/)
* Manually create a database in MySQL called "todos"!

3. Configure the application.properties file in the resources folder to match your MySQL settings (username/password).

4. Run the main class to start the application.

### How to use

Open Postman and use the following guide to sign up, login and handle data:

* Sign up:

POST:
`` localhost:8080/auth/register ``

JSON body:
``{
"firstName": "ellen",
"lastName": "halvardsson",
"email": "ellen@mail.com",
"password" : "12345"
}``

* Login:

POST:
`` localhost:8080/auth/login ``

JSON body:
``{
"username" : "ellen",
"password" : "12345"
}``

* Add a movie:


* Get a movie:


* Get all movies:


* Update a movie:


* Delete a  movie:

### Dependencies

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


