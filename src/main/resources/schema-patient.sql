CREATE DATABASE mediscreen_db CHARACTER SET utf8mb4;

USE mediscreen_db;

CREATE TABLE patient (
                id BIGINT AUTO_INCREMENT NOT NULL,
                lastname VARCHAR(100) NOT NULL,
                firstname VARCHAR(200) NOT NULL,
                birthdate VARCHAR (10) NOT NULL,
                sex CHAR NOT NULL,
                address VARCHAR(225) DEFAULT NULL,
                phone VARCHAR(30) DEFAULT NULL,
                usename VARCHAR(100) DEFAULT NULL,
                PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET=utf8mb4;

ALTER TABLE patient MODIFY COLUMN sex CHAR(1) COMMENT 'M or F';

INSERT INTO patient (id, lastname, firstname, birthdate, sex, address, phone)
VALUES
(1, "Ferguson", "Lucas", "1968-06-22", 'M', "2 Warren Street ", "387-866-1399"),
(2, "Rees", "Pippa", "1952-09-27", 'F', "745 West Valley Farms Drive", "628-423-0993"),
(3, "Arnold", "Edward", "1952-11-11", 'M', "599 East Garden Ave", "123-727-2779"),
(4, "Sharp", "Anthony", "1946-11-26", 'M', "894 Hall Street", "451-761-8383"),
(5, "Ince", "Wendy", "1958-06-29", 'F', "4 Southampton Road", "802-911-9975"),
(6, "Ross", "Tracey", "1949-12-07", 'F', "40 Sulphur Springs Dr", "131-396-5049"),
(7, "Wilson", "Claire", "1966-12-31", 'F', "12 Cobblestone St", "300-452-1091"),
(8, "Buckland", "Max", "1945-06-24", 'M', "193 Vale St", "833-534-0864"),
(9, "Clark", "Natalie", "1964-06-18", 'F', "12 Beechwood Road", "241-467-9197"),
(10, "Bailey", "Piers", "1959-06-28", 'M', "1202 Bumble Dr", "747-815-0557");

CREATE DATABASE mediscreen_db_test CHARACTER SET utf8mb4;
