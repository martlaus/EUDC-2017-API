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
VALUES ( 'Cafe closed in main building', 'We are sorry for causing discomfort. Please use the cafes and diners at other facilities around the campus while we are addressing the issue.', NOW());
INSERT INTO Card ( title, description, created)
VALUES ( 'Important notice', 'Sea in populo eleifend, ex ius stet feugait explicari, id eros nominati mei. Cu eum aeque debitis, pro paulo simul volumus ei, choro possit principes ad vis. At libris labore eum, mei porro consul laudem te, his et propriae omnesque consetetur. Quo ut mentitum accommodare. Graeco voluptaria no sea. Aliquam inimicus constituto sed no, per ei noster diceret. Facer ludus intellegat ius in, eligendi constituto duo cu, vide vulputate disputationi eu qui.', NOW());
INSERT INTO Card ( title, description, created)
VALUES ( 'Recent Winners', 'Below we have brief headlines to all the top debating competitions at the Stanford Payton Jordan Invite on Sunday night with links to the results and quick commentary from the live LetsRun.com thread. Also at the bottom are results from all the “A” qualifications on Sunday. Full results here. The biggest news of the night was 41 year old Bernard Lagat securing the win in phenomenal time', NOW());

-- Add TimerCards
INSERT INTO TimerCard ( title, description, created, enddate)
VALUES ('Round start notice', 'Your round will start at ICT-634 in: ', NOW(), '2016-05-23 23:59:00');