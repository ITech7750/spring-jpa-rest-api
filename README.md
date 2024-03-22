
## <p class="center" id="title1" > **RESTful web service на ЯП Kotlin c использованием платформы Spring Boot**</p>

### Настраиваем базу данных MySQL
Диалект и другие параметры бд в application.properties.
Сперва создаём базу данных app_db

```mysql
create database app_db;
use app_db;
```
Далее создадим таблицу сообщества
```mysql
create table community_table (
                                 id int(11) primary key auto_increment,
                                 title varchar(100) not null,
                                 description varchar(100) not null
);
```
Добавим тестовое сообщество командой 
```mysql
INSERT INTO app_db.community_table (id, title, description) VALUES (1, 'test', 'test')
```
Cоздаем таблицу пользователей
```mysql
create table user_table (
                                 id int(11) primary key auto_increment,
                                 title varchar(100) not null,
                                 information varchar(100) not null,
                                 community_id int(11),
                                 foreign key (community_id)  references community_table (Id)
);
```
Добавим тестовое поле для пользователя
```mysql
INSERT INTO app_db.user_table (id, title, information, community_id) VALUES (1, 'test', 'test', 1)
```
### Теперь мы можем запустить приложение и ознакомиться со Swagger аннотацией, доступной по ссылке:
http://127.0.0.1:8080/swagger-ui/index.html#/

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.3/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

