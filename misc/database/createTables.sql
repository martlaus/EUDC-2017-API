USE eudcApi;

-- Drop tables

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS AuthenticatedUser;
DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS Card_User;
DROP TABLE IF EXISTS TimerCard_User;
DROP TABLE IF EXISTS TimerCard;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS Feedback;
DROP TABLE IF EXISTS RoundLocation;
DROP TABLE IF EXISTS EventType;


SET foreign_key_checks = 1;

-- Create tables

CREATE TABLE User (
  id          BIGINT    AUTO_INCREMENT PRIMARY KEY,
  email       VARCHAR(255) UNIQUE NOT NULL,
  password    VARCHAR(255),
  role        VARCHAR(255)        NOT NULL,
  tabbieToken VARCHAR(255),
  created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
  pinned      BOOLEAN       NOT NULL,
  sendPushAll BOOLEAN       NOT NULL
);


CREATE TABLE TimerCard (
  id           BIGINT            AUTO_INCREMENT PRIMARY KEY,
  title        VARCHAR(255) NOT NULL,
  locationId   VARCHAR(255) NOT NULL,
  fullLocation VARCHAR(255) NOT NULL,
  team         VARCHAR(255) NOT NULL,
  topic        VARCHAR(255) NOT NULL,
  unixtime     VARCHAR(255),
  created      TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  enddate      TIMESTAMP    NULL DEFAULT NULL
);

CREATE TRIGGER updateDate
BEFORE INSERT ON TimerCard
FOR EACH ROW
  SET NEW.enddate = from_unixtime(NEW.unixtime);

CREATE TABLE Location (
  id      BIGINT    AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  lng     VARCHAR(255) NOT NULL,
  lat     VARCHAR(255) NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE RoundLocation (
  id      BIGINT    AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  imgurl  VARCHAR(512) NOT NULL,
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

CREATE TABLE EventType (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  eventType VARCHAR(255) NOT NULL,
  color     VARCHAR(255) NOT NULL,
  eventIcon VARCHAR(255) NOT NULL
);

CREATE TABLE Event (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255)                        NOT NULL,
  description VARCHAR(1000),
  location    VARCHAR(255)                        NOT NULL,
  eventType   BIGINT                              NOT NULL,
  startTime   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  endTime     TIMESTAMP DEFAULT now()             NOT NULL,

  FOREIGN KEY (eventType)
  REFERENCES EventType (id)
    ON DELETE RESTRICT
);

CREATE TABLE Feedback (
  id      BIGINT    AUTO_INCREMENT PRIMARY KEY,
  content VARCHAR(255) NOT NULL,
  user    BIGINT       NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  FOREIGN KEY (user)
  REFERENCES User (id)
    ON DELETE RESTRICT
);
