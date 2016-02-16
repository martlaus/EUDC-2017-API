USE eudcApi;

-- Add Users
INSERT INTO User (id, email, password, role, created)
VALUES (1, 'admin@admin.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (2, 'user@user.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (3, 'asd@asd.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (4, 'mart@mart.ml', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'ADMIN', NOW());

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


-- Add Cards
INSERT INTO Card (id, title, description, created)
VALUES (1, 'Lorem Ipsum', 'Sed omnium volumus voluptua te', NOW());
INSERT INTO Card (id, title, description, created)
VALUES (2, 'Ipsum Lorem', 'At vis quis dolor inermis', NOW()); 
INSERT INTO Card (id, title, description, created)
VALUES (3, 'Delirium Tremens', 'His no sensibus moderatius', NOW());
