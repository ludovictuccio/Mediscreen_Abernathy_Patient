<img src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/> * * *  <img src="https://img.shields.io/badge/spring%20-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>  * * *  <img src="https://img.shields.io/badge/docker%20-%230db7ed.svg?&style=for-the-badge&logo=docker&logoColor=white"/> * * * [![made-with-sphinx-doc](https://img.shields.io/badge/Made%20with-Gradle-1f425f.svg)](https://www.sphinx-doc.org/)

# Mediscreen_Abernathy - Patient

- OpenClassrooms - Project 9 / Mediscreen_Abernathy App - Patient microservice

- The application **'Mediscreen_Abernathy'** is run with **Java 11**, **Maven** and **Spring Boot v2.4.1** .


## Technical

Mediscreen_Abernathy is composed of 4 microservices:

1. **API Gateway**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Api-Gateway <br/>
The API Gateway, used on port 8080. <br/>
---

2. **Patient**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Patient <br/>
The microservice used for acces to patient's medical record (creation, consultation, modifications), used on port 8081. <br/>
---

3. **Notes**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Notes <br/>
The microservice used for add notes to patient's medical record, used on port 8082. <br/>
---

4. **Reports**: https://github.com/ludovictuccio/Mediscreen_Abernathy_Reports <br/>
The microservice used for generate patient's diabete reports, used on port 8083. <br/>
---

**Feign** is used for the microservices relations.

---

## Installing

1. Install **Java**: https://www.oracle.com/java/technologies/javase-downloads.html <br/>

2. Install **Maven**: https://maven.apache.org/install.html

3. Install **Lombok** in your IDE before import project: https://www.baeldung.com/lombok-ide <br/>
You must execute an external JAR.


## To run microservice:

- **With your IDE**: refer to **application.properties** to set valid proxies url

- **With Docker**: the jar hosted on GitHub is for a Docker deployment ("localhost" changed with the image Docker)


## For Docker deploiement:

1. Install Docker Desktop: <br/>
https://docs.docker.com/docker-for-windows/ or https://docs.docker.com/docker-for-mac/

2. To use the **Dockerfile**, you must run on the package root: 
- `docker build -t mediscreen-patient .`
- `docker run -d -p 8081:8081 mediscreen-patient`

3. To run all microservices on the same network, with a Docker-Compose deploiement: <br/>
If you want to deploy all TourGuide microservices, use the **docker-compose.yml** on the package root, after each Dockerfile deployment for the 4 microservices, running:
- `docker network create tourguide-net`
- `docker-compose up -d`


## API documentation

- **Swagger 3:** http://localhost:8081/swagger-ui/index.html#/


## API REST Endpoints

# Patient microservice

> **POST** - Add new patient's medical record <br/>
http://localhost:8081/api/patient <br/>
**Need body** with: lastName, firstName, birthdate(yyyy-MM--dd), sex, address, phone & usename

> **PUT** - Update patient's personal informations<br/>
http://localhost:8081/api/patient <br/>
**Need parameter**: patId = the patient id <br/>
**Need body** with: lastName, firstName, birthdate(yyyy-MM--dd), sex, address, phone & usename

> **GET** - Get all patient's list <br/>
http://localhost:8081/api/patient/list <br/>

> **GET** - Search a patient by lastName <br/>
http://localhost:8081/api/patient/searchPatient <br/>
**Need parameter**: lastName <br/>

> **GET** - Get a patient's medical record <br/>
http://localhost:8081/api/patient <br/>
**Need parameter**: patId = the patient id <br/>

# Notes microservice

> **POST** - Add new patient's note <br/>
http://localhost:8082/api/note <br/>
**Need body** with: patId (the patient's id), patientLastname & note

> **PUT** - Update a patient's note <br/>
http://localhost:8082/api/note <br/>
**Need parameter**: id = the note's id <br/>
**Need body** with: patId (the patient's id), patientLastname & note

> **GET** - Get all notes list <br/>
http://localhost:8082/api/note/all <br/>

> **GET** - Get all patient's notes list <br/>
http://localhost:8082/api/note <br/>
**Need parameter**: patId = the patient's id <br/>

## Testing

The app has unit tests and integration tests written. <br/>
You must launch `mvn test` or build with `mvn clean package` (all reports available in *"/target"*).
