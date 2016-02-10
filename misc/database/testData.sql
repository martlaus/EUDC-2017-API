USE EudcApi;

-- Add Users
INSERT INTO User (id, email, password, role, created)
VALUES (1, 'admin@admin.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (2, 'user@user.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (3, 'asd@asd.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


