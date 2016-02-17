-- User data
INSERT INTO User (id, email, password, created, role) VALUES (1, 'admin@admin.kz', 'kaarliema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (2, 'user@user.kz', 'siimuema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (3, 'mart@mart.kz', '$2a$10$XEftKIY/9c/aiKRZNjwdouidY09XNVYgU86hgHnXwgIguCUqHOyjO', '1999-02-02 06:00:01', 'USER');

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');

-- Card data
INSERT INTO Card (id, title, description, created) VALUES (1, 'Lorem Ipsum', 'Sed omnium volumus voluptua te', '1999-02-02 06:00:01');
INSERT INTO Card (id, title, description, created) VALUES (2, 'Ipsum Lorem', 'At vis quis dolor inermis', '1999-02-02 06:00:01'); 
INSERT INTO Card (id, title, description, created) VALUES (3, 'Delirium Tremens', 'His no sensibus moderatius', '1999-02-02 06:00:01');
