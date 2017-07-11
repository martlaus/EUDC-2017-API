USE eudcApi;


-- Add Users
INSERT INTO User (email, password, role, created)
VALUES ('admin@admin.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'ADMIN', NOW());
INSERT INTO User (email, password, role, created)
VALUES ('user@user.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User (email, password, role, created)
VALUES ('asd@asd.kz', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'USER', NOW());
INSERT INTO User (email, password, role, created)
VALUES ('mart@mart.ml', '$2a$10$Z8tPz8Nqygv7HZtqHHyoQuap2Zra3ZerEEyjJ.gahdreoeO7UFpxO', 'ADMIN', NOW());

INSERT INTO AuthenticatedUser (user_id, token) VALUES (1, 'superUniqueToken');


-- Add Cards
INSERT INTO Card ( title, description, created, pinned, sendPushAll)
VALUES ( 'Cafe closed in main building', 'We are sorry for causing discomfort. Please use the cafes and diners at other facilities around the campus while we are addressing the issue.', NOW(), false, false);
INSERT INTO Card ( title, description, created, pinned, sendPushAll)
VALUES ( 'Important notice', 'Sea in populo eleifend, ex ius stet feugait explicari, id eros nominati mei. Cu eum aeque debitis, pro paulo simul volumus ei, choro possit principes ad vis. At libris labore eum, mei porro consul laudem te, his et propriae omnesque consetetur. Quo ut mentitum accommodare. Graeco voluptaria no sea. Aliquam inimicus constituto sed no, per ei noster diceret. Facer ludus intellegat ius in, eligendi constituto duo cu, vide vulputate disputationi eu qui.', NOW(), false, false);
INSERT INTO Card ( title, description, created, pinned, sendPushAll)
VALUES ( 'Recent Winners', 'Below we have brief headlines to all the top debating competitions at the Stanford Payton Jordan Invite on Sunday night with links to the results and quick commentary from the live LetsRun.com thread. Also at the bottom are results from all the qualifications on Sunday. Full results here. The biggest news of the night was 41 year old Bernard Lagat securing the win in phenomenal time', NOW(), true, false);
INSERT INTO Card ( title, description, created, pinned, sendPushAll)
VALUES ( 'Video of the day', 'Here is a recap video of the 3rd day https://www.youtube.com/watch?v=dQw4w9WgXcQ EUDC Newcastle 2009', NOW(), false, false);


-- Add Locations
INSERT INTO Location (name, lat, lng, created)
VALUES ('Tallinn Harbour', '59.443634', '24.767353', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Tallinn University of Technology', '59.395916', '24.671871', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Tallinn Bus Station', '59.427567', '24.773679', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Tallinn Airport', '59.416500', '24.799281', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Tallinn Song Festival Grounds', '59.444686', '24.807330', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Pirita SPA Hotel', '59.464808', '24.823108', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Telliskivi Creative City', '59.440103', '24.729530', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Nordea Concert Hall', '59.433227', '24.751926', NOW());
INSERT INTO Location (name, lat, lng, created)
VALUES ('Kultuurikatel', '59.444416', '24.750546', NOW());

-- Add Round Locations
INSERT INTO RoundLocation (name, imgurl, created)
VALUES ('U03', 'http://i.imgur.com/h1YP8oA.jpg', NOW());


INSERT INTO RoundLocation (name, imgurl, created)
VALUES ('U04', 'http://i.imgur.com/lAslKTf.jpg', NOW());

-- Add TimerCards
INSERT INTO TimerCard (title, fullLocation, locationId, team, topic, unixtime, created)
VALUES ('Round start notice', 'U03 room 123', 1, 'Affirmative', 'Smoking is good for your health.', '1492732200', NOW());

INSERT INTO TimerCard (title, fullLocation, locationId, team, topic, unixtime, created)
VALUES ('Round start notice', 'U04 room 321', 2, 'Negative', 'Earth is flat.', '1492732200', NOW());

-- Add Cards to Users (All users basically have a list of cards that they have)

INSERT INTO Card_User(card, user) VALUES (1,1);
INSERT INTO Card_User(card, user) VALUES (2,1);
INSERT INTO Card_User(card, user) VALUES (3,1);
INSERT INTO Card_User(card, user) VALUES (1,2);
INSERT INTO Card_User(card, user) VALUES (1,3);
INSERT INTO Card_User(card, user) VALUES (2,2);
INSERT INTO Card_User(card, user) VALUES (3,2);
INSERT INTO Card_User(card, user) VALUES (3,3);
INSERT INTO Card_User(card, user) VALUES (4,3);

-- Add TimerCards to Users
INSERT INTO TimerCard_User(timercard, user) VALUES (1,3);
INSERT INTO TimerCard_User(timercard, user) VALUES (1,1);
INSERT INTO TimerCard_User(timercard, user) VALUES (2,2);


-- Add Test event types

INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (1,'catering', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (2,'transport', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (3,'organisational event', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (4,'debate', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (5,'semifinal', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (6,'final', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (7,'social', 'purple', 'ion-mic-c');
INSERT INTO EventType (id, eventType, color, eventIcon) VALUES (8,'miscellaneous', 'purple', 'ion-mic-c');

-- Add Test events
INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (1,
                                                                                            'Registration',
                                                                                            'All participants are expected to arrive and register on this day.',
                                                                                            '2017-08-14 10:00:00',
                                                                                            '2017-08-14 16:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (2,
                                                                                            'EUDC pre-council meeting',
                                                                                            'EUDC pre-council meeting, registration continues.',
                                                                                            '2017-08-14 16:00:00',
                                                                                            '2017-08-14 18:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (3,
                                                                                            'Speaker, judge and equity briefings',
                                                                                            'Speaker, judge and equity briefings, registration continues.',
                                                                                            '2017-08-14 18:00:00',
                                                                                            '2017-08-14 19:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (4,
                                                                                            'Opening Ceremony and Socials',
                                                                                            'Opening Ceremony, registration continues.',
                                                                                            '2017-08-14 19:00:00',
                                                                                            '2017-08-15 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (5,
                                                                                            'Late arrivals',
                                                                                            'In case needed please contact organisers.',
                                                                                            '2017-08-15 00:00:00',
                                                                                            '2017-08-15 06:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (6,
                                                                                            'Breakfast',
                                                                                            'Breakfast provided in accommodation.',
                                                                                            '2017-08-15 07:00:00',
                                                                                            '2017-08-15 08:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (7,
                                                                                            'Transfer to TTU',
                                                                                            'Transfer to TTU.',
                                                                                            '2017-08-15 09:00:00',
                                                                                            '2017-08-15 09:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (8,
                                                                                            'Sign in at TTU',
                                                                                            'Sign in at TTU.',
                                                                                            '2017-08-15 09:30:00',
                                                                                            '2017-08-15 10:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (9,
                                                                                            'Round 1',
                                                                                            'The first debate of the tournament.',
                                                                                            '2017-08-15 10:00:00',
                                                                                            '2017-08-15 12:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (10,
                                                                                            'Lunch',
                                                                                            'Lunch will be served at TTU.',
                                                                                            '2017-08-15 12:45:00',
                                                                                            '2017-08-15 14:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (11,
                                                                                            'Round 2',
                                                                                            'Round 2.',
                                                                                            '2017-08-15 14:00:00',
                                                                                            '2017-08-15 16:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (12,
                                                                                            'Round 3',
                                                                                            'Round 3.',
                                                                                            '2017-08-15 16:00:00',
                                                                                            '2017-08-15 18:15:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (13,
                                                                                            'Dinner',
                                                                                            'Dinner.',
                                                                                            '2017-08-15 18:15:00',
                                                                                            '2017-08-15 19:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (14,
                                                                                            'Transfer to Hotel',
                                                                                            'Transfer to Hotel.',
                                                                                            '2017-08-15 19:30:00',
                                                                                            '2017-08-15 20:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (15,
                                                                                            'Socials',
                                                                                            'Socials.',
                                                                                            '2017-08-15 21:00:00',
                                                                                            '2017-08-16 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);


INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (16,
                                                                                            'Breakfast',
                                                                                            'Breakfast provided in accommodation.',
                                                                                            '2017-08-16 07:00:00',
                                                                                            '2017-08-16 08:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (17,
                                                                                            'Transfer to TTU',
                                                                                            'Transfer to TTU.',
                                                                                            '2017-08-16 09:00:00',
                                                                                            '2017-08-16 09:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (18,
                                                                                            'Sign in at TTU',
                                                                                            'Sign in at TTU.',
                                                                                            '2017-08-16 09:30:00',
                                                                                            '2017-08-16 10:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (19,
                                                                                            'Round 4',
                                                                                            'Round 4.',
                                                                                            '2017-08-16 10:00:00',
                                                                                            '2017-08-16 12:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (20,
                                                                                            'Lunch',
                                                                                            'Lunch at TTU.',
                                                                                            '2017-08-16 12:45:00',
                                                                                            '2017-08-16 14:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (21,
                                                                                            'Round 5',
                                                                                            'Round 5.',
                                                                                            '2017-08-16 14:00:00',
                                                                                            '2017-08-16 16:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (22,
                                                                                            'Round 6',
                                                                                            'Round 6.',
                                                                                            '2017-08-16 16:00:00',
                                                                                            '2017-08-16 18:15:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (23,
                                                                                            'Dinner',
                                                                                            'Dinner.',
                                                                                            '2017-08-16 18:15:00',
                                                                                            '2017-08-16 19:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (24,
                                                                                            'Transfer to Hotel',
                                                                                            'Transfer to Hotel.',
                                                                                            '2017-08-16 19:30:00',
                                                                                            '2017-08-16 20:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (25,
                                                                                            'Socials',
                                                                                            'Socials.',
                                                                                            '2017-08-16 21:00:00',
                                                                                            '2017-08-17 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (26,
                                                                                            'Breakfast',
                                                                                            'Breakfast provided in accommodation.',
                                                                                            '2017-08-17 07:00:00',
                                                                                            '2017-08-17 08:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (27,
                                                                                            'Transfer to TTU',
                                                                                            'Transfer to TTU.',
                                                                                            '2017-08-17 09:00:00',
                                                                                            '2017-08-17 09:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (28,
                                                                                            'Sign in at TTU',
                                                                                            'Sign in at TTU.',
                                                                                            '2017-08-17 09:30:00',
                                                                                            '2017-08-17 10:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (29,
                                                                                            'Round 7',
                                                                                            'Round 7.',
                                                                                            '2017-08-17 10:00:00',
                                                                                            '2017-08-17 12:45:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (30,
                                                                                            'Lunch at TTU',
                                                                                            'Lunch at TTU.',
                                                                                            '2017-08-17 12:45:00',
                                                                                            '2017-08-17 14:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (31,
                                                                                            'Round 8',
                                                                                            'Round 8.',
                                                                                            '2017-08-17 14:00:00',
                                                                                            '2017-08-17 16:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (32,
                                                                                            'Round 9',
                                                                                            'Round 9.',
                                                                                            '2017-08-17 16:00:00',
                                                                                            '2017-08-17 18:15:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (33,
                                                                                            'Dinner',
                                                                                            'Dinner.',
                                                                                            '2017-08-17 18:15:00',
                                                                                            '2017-08-17 19:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (34,
                                                                                            'Transfer to Hotel',
                                                                                            'Transfer to Hotel.',
                                                                                            '2017-08-17 19:30:00',
                                                                                            '2017-08-17 20:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (35,
                                                                                            'Break night socials',
                                                                                            'Break night socials.',
                                                                                            '2017-08-17 21:00:00',
                                                                                            '2017-08-18 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (36,
                                                                                            'Breakfast',
                                                                                            'Breakfast provided in accommodation.',
                                                                                            '2017-08-18 07:30:00',
                                                                                            '2017-08-18 09:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (37,
                                                                                            'Transfer',
                                                                                            'Transfer.',
                                                                                            '2017-08-18 09:30:00',
                                                                                            '2017-08-18 10:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (38,
                                                                                            'Open Quarter',
                                                                                            'Open Quarter.',
                                                                                            '2017-08-18 10:30:00',
                                                                                            '2017-08-18 12:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (39,
                                                                                            'ESL Quarter',
                                                                                            'ESL Quarter.',
                                                                                            '2017-08-18 12:00:00',
                                                                                            '2017-08-18 13:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            4);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (40,
                                                                                            'Lunch',
                                                                                            'Lunch.',
                                                                                            '2017-08-18 13:30:00',
                                                                                            '2017-08-18 15:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (41,
                                                                                            'ESL Semi',
                                                                                            'ESL Semi.',
                                                                                            '2017-08-18 15:00:00',
                                                                                            '2017-08-18 16:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            5);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (42,
                                                                                            'Open Semi',
                                                                                            'Open Semi.',
                                                                                            '2017-08-18 16:30:00',
                                                                                            '2017-08-18 18:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            5);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (43,
                                                                                            'Dinner',
                                                                                            'Dinner.',
                                                                                            '2017-08-18 18:00:00',
                                                                                            '2017-08-18 19:30:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (44,
                                                                                            'Transfer to Hotel',
                                                                                            'Transfer to Hotel.',
                                                                                            '2017-08-18 19:30:00',
                                                                                            '2017-08-18 20:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            2);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (45,
                                                                                            'Socials',
                                                                                            'Socials.',
                                                                                            '2017-08-18 21:00:00',
                                                                                            '2017-08-19 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (46,
                                                                                            'Breakfast',
                                                                                            'Breakfast provided in accommodation.',
                                                                                            '2017-08-19 07:00:00',
                                                                                            '2017-08-19 11:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            1);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (47,
                                                                                            'EUDC Council meeting',
                                                                                            'EUDC Council meeting.',
                                                                                            '2017-08-19 10:00:00',
                                                                                            '2017-08-19 13:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (49,
                                                                                            'ESL Final',
                                                                                            'ESL Final.',
                                                                                            '2017-08-19 17:00:00',
                                                                                            '2017-08-19 19:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            6);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (50,
                                                                                            'Open Final',
                                                                                            'Open Final.',
                                                                                            '2017-08-19 19:00:00',
                                                                                            '2017-08-19 21:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            6);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (51,
                                                                                            'Dinner + Awards Ceremony + Farewell party',
                                                                                            'Dinner + Awards Ceremony + Farewell party.',
                                                                                            '2017-08-19 21:00:00',
                                                                                            '2017-08-20 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            7);

INSERT INTO Event (id, title, description, startTime, endTime, location, eventType) VALUES (52,
                                                                                            'Departures',
                                                                                            'Departures.',
                                                                                            '2017-08-20 00:00:00',
                                                                                            '2017-08-21 00:00:00',
                                                                                            'Tallinn University of Technology',
                                                                                            3);

-- Add Test feedback

INSERT INTO Feedback (id, content, user) VALUES (1, 'It is a decent app guys!', 1);
INSERT INTO Feedback (id, content, user) VALUES (2, 'Ma ei tea noh, see tabbie liidestus on ikka kehvake', 1);
INSERT INTO Feedback (id, content, user) VALUES (3, 'Where can I change the color scheme?!', 2);






