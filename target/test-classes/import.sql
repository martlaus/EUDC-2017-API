INSERT INTO User (id, email, password, created, role) VALUES (1, 'admin@admin.kz', 'kaarliema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (2, 'user@user.kz', 'siimuema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (3, 'mart@mart.kz', '$2a$10$V4EDaQx2JnMJ6mPgH7Kpbu2fmjqTCLwr17FaMFqDNJYgkItnZIpJ6', '1999-02-02 06:00:01', 'USER');

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


