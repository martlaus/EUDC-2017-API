USE eudcApi;

ALTER TABLE User
  ADD tournamentRole VARCHAR(255),
  ADD tabbieId BIGINT(20);