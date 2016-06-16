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
VALUES ( 'Recent Winners', 'Below we have brief headlines to all the top debating competitions at the Stanford Payton Jordan Invite on Sunday night with links to the results and quick commentary from the live LetsRun.com thread. Also at the bottom are results from all the �A� qualifications on Sunday. Full results here. The biggest news of the night was 41 year old Bernard Lagat securing the win in phenomenal time', NOW());

-- Add Locations
INSERT INTO Location (id, name, lat, lng, created)
VALUES (1, 'Tallinn Harbour', '59.443634', '24.767353', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (2, 'Tallinn University of Technology', '59.395916', '24.671871', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (3, 'Tallinn Bus Station', '59.427567', '24.773679', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (4, 'Tallinn Airport', '59.416500', '24.799281', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (5, 'Tallinn Song Festival Grounds', '59.444686', '24.807330', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (6, 'Pirita SPA Hotel', '59.464808', '24.823108', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (7, 'Telliskivi Creative City', '59.440103', '24.729530', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (8, 'Nordea Concert Hall', '59.433227', '24.751926', NOW());
INSERT INTO Location (id, name, lat, lng, created)
VALUES (9, 'Kultuurikatel', '59.444416', '24.750546', NOW());

-- Add TimerCards
INSERT INTO TimerCard ( title, description, created, enddate)
VALUES ('Round start notice', 'Your round will start at ICT-634 in: ', NOW(), '2016-06-18 13:55:00');

-- Add Cards to Users (All users basically have a list of cards that they have)

INSERT INTO Card_User(card, user) VALUES (1,1);
INSERT INTO Card_User(card, user) VALUES (2,1);
INSERT INTO Card_User(card, user) VALUES (3,1);
INSERT INTO Card_User(card, user) VALUES (1,2);
INSERT INTO Card_User(card, user) VALUES (1,3);
INSERT INTO Card_User(card, user) VALUES (2,2);
INSERT INTO Card_User(card, user) VALUES (3,2);
INSERT INTO Card_User(card, user) VALUES (3,3);

-- Add TimerCards to Users 
INSERT INTO TimerCard_User(timercard, user) VALUES (1,3);
INSERT INTO TimerCard_User(timercard, user) VALUES (1,1);


