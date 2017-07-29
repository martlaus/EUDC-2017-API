USE eudcApi;

CREATE TABLE Round (
  id          BIGINT PRIMARY KEY,
  label       VARCHAR(255),
  motion      VARCHAR(255),
  infoslide   VARCHAR(255),
  prepStarted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  roundLink   VARCHAR(255)
);



