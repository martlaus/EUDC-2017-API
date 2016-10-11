USE eudcApi;

-- Drop tables

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS AuthenticatedUser;
DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS TimerCard;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Event;

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
  id          BIGINT    AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255)  NOT NULL,
  description VARCHAR(1000) NOT NULL,
  created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  pinned      BOOLEAN       NOT NULL
);


CREATE TABLE TimerCard (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255)                        NOT NULL,
  description VARCHAR(1000)                       NOT NULL,
  created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  enddate     TIMESTAMP DEFAULT now()             NOT NULL
);


CREATE TABLE Location (
  id      BIGINT    AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  lng     VARCHAR(255) NOT NULL,
  lat     VARCHAR(255) NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Card_User (
  card BIGINT NOT NULL,
  user BIGINT NOT NULL,

  PRIMARY KEY (card, user),

  FOREIGN KEY (card)
  REFERENCES Card (id)
    ON DELETE RESTRICT,

  FOREIGN KEY (user)
  REFERENCES User (id)
    ON DELETE RESTRICT
);

CREATE TABLE TimerCard_User (
  timercard BIGINT NOT NULL,
  user      BIGINT NOT NULL,

  PRIMARY KEY (timercard, user),

  FOREIGN KEY (timercard)
  REFERENCES TimerCard (id)
    ON DELETE RESTRICT,

  FOREIGN KEY (user)
  REFERENCES User (id)
    ON DELETE RESTRICT
);

CREATE TABLE Event (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255)                        NOT NULL,
  description VARCHAR(1000),
  startTime   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  endTime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL

);

