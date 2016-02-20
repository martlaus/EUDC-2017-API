USE eudcApi;

-- Add Users
INSERT INTO User ( email, password, role, created)
VALUES ('admin@admin.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User ( email, password, role, created)
VALUES ('user@user.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User ( email, password, role, created)
VALUES ('asd@asd.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User ( email, password, role, created)
VALUES ('mart@mart.ml', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'ADMIN', NOW());

INSERT INTO AuthenticatedUser (user_id, token)
VALUES (1, 'superUniqueToken');


-- Add Cards
INSERT INTO Card ( title, description)
VALUES ( 'Lorem Ipsum', 'Sed omnium volumus voluptua te');
INSERT INTO Card ( title, description)
VALUES ( 'Ipsum Lorem', 'At vis quis dolor inermis');
INSERT INTO Card ( title, description)
VALUES ( 'Delirium Tremens', 'His no sensibus moderatius');

INSERT INTO CardStatus (is_card_swiped,owner_id)
     SELECT  is_card_swiped, authenticateduser.id FROM Card INNER JOIN authenticateduser WHERE is_card_swiped = 0;
