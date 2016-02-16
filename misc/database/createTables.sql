USE eudcApi;

-- Drop tables

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS AuthenticatedUser;
DROP TABLE IF EXISTS Card;




SET foreign_key_checks = 1;

-- Create tables

CREATE TABLE User (
  id       BIGINT    AUTO_INCREMENT PRIMARY KEY,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL,
  role     VARCHAR(255)        NOT NULL,
  created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE AuthenticatedUser (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT              NOT NULL,
  token   VARCHAR(255) UNIQUE NOT NULL,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT
);



CREATE TABLE Card (
  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
  title    VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




