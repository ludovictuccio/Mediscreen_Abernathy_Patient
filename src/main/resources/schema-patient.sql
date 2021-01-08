CREATE DATABASE mediscreen_db CHARACTER SET utf8mb4;
CREATE DATABASE mediscreen_db_test CHARACTER SET utf8mb4;
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
