USE eudcApi;

-- Add Users
INSERT INTO User (id, email, password, role, created)
VALUES (1, 'admin@admin.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (2, 'user@user.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (3, 'asd@asd.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (4, 'mart@mart.ml', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'ADMIN', NOW());

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


-- Add Cards
INSERT INTO Card ( title, description, created)
VALUES ( 'Lorem Ipsum', 'Sed omnium volumus voluptua te', NOW());
INSERT INTO Card ( title, description, created)
VALUES ( 'Ipsum Lorem', 'At vis quis dolor inermis', NOW());
INSERT INTO Card ( title, description, created)
VALUES ( 'Delirium Tremens', 'His no sensibus moderatius', NOW());
