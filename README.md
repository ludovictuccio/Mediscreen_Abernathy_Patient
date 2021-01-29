<img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/> * * *  <img src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>  * * *  <img src="https://img.shields.io/badge/docker%20-%230db7ed.svg?&style=for-the-badge&logo=docker&logoColor=white"/>

# Mediscreen_Abernathy - Patient

OpenClassrooms - Project 9 <br/>
Mediscreen_Abernathy App - Patient microservice <br/>

---

This application is made in two parts: 
- RestControllers for the API REST
- Controllers with Spring MVC for Thymeleaf frontend interface

---

Mediscreen_Abernathy is composed of 4 microservices:

1. **API Gateway**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Api-Gateway <br/>
The API Gateway, used on port 8080. <br/>
Used for the API REST. <br/>
---

2. **Patient**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Patient <br/>
The microservice used for access to patient's medical record (search, consultation, creation, update), used on port 8081. <br/>
---

3. **Notes**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Notes <br/>
The microservice used for add patient's notes, used on port 8082. <br/>
---

4. **Reports**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Reports <br/>
The microservice used for generate patient's diabetes assessment reports, used on port 8083. <br/>
---

**Feign** is used for the microservices relations.

---

## Informations / Technical

- **Java** 11 - **Maven** - **Spring Boot** 2.4.1
- **Spring Data Jpa** - **Hibernate** 5
- **MySql** 8 - **MongoDB** - **Jasypt**
- **Thymeleaf** - **Bootstrap** v4.3.1 - **JQuery**
- **Docker** - **Docker-Compose** - **Feign**
- **Swagger** 3 - **Devtools** - **ModelMapper** - **Javax Validation**
- **JaCoCo** - **Surefire** - **Checkstyle** - **Spotbugs** - **Lombok**

## Installing

1. Install **Java**: https://www.oracle.com/java/technologies/javase-downloads.html <br/>

2. Install **Maven**: https://maven.apache.org/install.html

3. Install **Lombok** in your IDE before import project: https://www.baeldung.com/lombok-ide <br/>
(you must execute an external JAR)

4. Install **MySql**: https://dev.mysql.com/downloads/installer/

5. Install **MongoDb**: https://docs.mongodb.com/manual/administration/install-community/


## Databases - SQL & NOSQL

- **Microservice Patient**: <br/>
the file **schema-patients.sql** (available in *"/src/main/resources"*) contains scrypt SQL to create patients database
- **Microservice Notes**:  <br/>
in *"/src/main/resources"* : the file **data-test.json** contains data tests and **data-notes.CSV** contains real datas for the ten patients (UTF8 applied)


## To run microservice with:

- **IDE**: refer to **application.properties** to set valid proxies url with localhost

- **Docker**: the **application.properties** is written for the Docker deploiement. The Dockerfile will build the microservice and run the jar to run it.


## For Docker deploiement:

1. Install Docker Desktop: <br/>
https://docs.docker.com/docker-for-windows/ or https://docs.docker.com/docker-for-mac/

2. The **Dockerfile** will build the microservice and run the jar to run it. <br/>
To use it, you must run on the package root: 
- `docker build -t mediscreen-patient .`
- `docker run -d -p 8081:8081 mediscreen-patient`

3. To run all microservices on the same network, with a Docker-Compose deploiement: <br/>
If you want to deploy all TourGuide microservices, use the **docker-compose.yml** on the package root, after each Dockerfile deployment for the 4 microservices, running:
- `docker network create tourguide-net`
- `docker-compose up -d`

This will launch:
- MySql database on the port 3307
- MongoDB local server and use a database named *mediscreen-docker* and collection *notes*


## API documentation

- **Swagger 3:** http://localhost:8081/swagger-ui/index.html#/


## API REST Endpoints

### Patient microservice

> **POST** - Add new patient's medical record <br/>
http://localhost:8081/api/patient <br/>
**Need body** with: lastName, firstName, birthdate(yyyy-MM--dd), sex, address, phone & usename

> **PUT** - Update patient's personal informations<br/>
http://localhost:8081/api/patient <br/>
**Need body** with: lastName, firstName, birthdate(yyyy-MM--dd), sex, address, phone & usename

> **GET** - Get all patient's list <br/>
http://localhost:8081/api/patient/list <br/>

> **GET** - Search a patient by lastName <br/>
http://localhost:8081/api/patient/searchPatient <br/>
**Need parameter**: lastName <br/>

> **GET** - Get a patient's medical record <br/>
http://localhost:8081/api/patient <br/>
**Need parameter**: patId = the patient id <br/>

### Notes microservice

> **POST** - Add new patient's note <br/>
http://localhost:8082/api/note <br/>
**Need body** with: 'lastName', 'firstName', 'note' <br/>

> **PUT** - Update a patient's note <br/>
http://localhost:8082/api/note <br/>
**Need parameter**: id = the note's id <br/>
**Need body** with: 'lastName', 'firstName', 'note' <br/>

> **GET** - Get all notes list <br/>
http://localhost:8082/api/note/all <br/>

> **GET** - Get all patient's notes list <br/>
http://localhost:8082/api/note <br/>
**Need parameter**: 'lastName' & 'firstName' <br/>

> **GET** - Get all patient's notes DTO <br/>
http://localhost:8082/api/note/getAllPatientsNoteDto <br/>
**Need parameter**: 'lastName' & 'firstName' <br/>


### Reports microservice

> **GET** - Get patient's personal informations DTO <br/>
http://localhost:8083/api/reports/getPatientPersonalInformations <br/>
**Need parameter**: patId = the patient's id <br/>

> **GET** - Get all patient's notes DTO list <br/>
http://localhost:8083/api/reports/getAllPatientsNoteDto <br/>
**Need parameter**: lastName & firstName <br/>

> **GET** - Get patient's diabetes assesment report <br/>
http://localhost:8083/api/reports/report <br/>
**Need parameter**: patId = the patient's id <br/>

## Testing

The app has unit tests and integration tests written. <br/>
You must launch `mvn test` or build with `mvn site` (all reports available in *"/target"*).

## JaCoCo report

![Screenshot](Jacoco.PNG)

## Surefire report

![Screenshot](Surefire.PNG)
