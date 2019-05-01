# DatingApp; backend




To run
---------


To build
---------

```
gradle build -x test

```

**NB!** Make sure Annotation Processors are enabled (Under **Preferences** -> **Compiler** -> **Annotation Processors** -> **Enable annotation proccessing**) and Lombok plugin is installed.

Make also sure these dependencies could be found in * *build.gradle* *

```
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'

```


Built with
---------

- Java SE 9
- Spring Framework
- PostgreSQL open-source relational database

Tools used for better development proccess
---------

- Java integrated development environment IntelliJ
- Swagger open-source software framework for managing RESTful Web services
  
SWAGGER: 

NB! (For using swagger comment config/WebSecurityConfig)

LINK: http://localhost:8081/swagger-ui.html#





