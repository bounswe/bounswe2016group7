# noinspection SqlNoDataSourceInspectionForFile
INSERT INTO users (id, email, association, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (nextval('USERS_SEQ'), 'ugur@admin.com', 'Bogazici University', true, 'Ugur', 'm', NULL, 'Bor', NULL, '$2a$10$xHrHghoH88RS1OZ7ovl1fO22Zmz4RmwETKrQe.SLz9hJQsiHZ4acy', 'ugurbor');
INSERT INTO users (id, email, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (nextval('USERS_SEQ'), 'one@admin.com', true, 'Osman', 'm', NULL, 'Kral', NULL, '$2a$10$OIgL3cS5enwzHk9WtFGp7uaQ085JTvxdrhiD.6n98SpHsmhdgHouK', 'userone');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_EXPLORER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_CREATOR');
INSERT INTO AUTHORITY (ID, NAME) VALUES (3, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 3);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);

INSERT INTO TOPIC_PACKS (TOPIC_PACK_ID, COUNT, CREATE_DATE, NAME, USER_ID) VALUES (nextval('TOPICPACK_SEQ'), 9, '09/11/2016 18:19', 'TopicPack1', 1);
INSERT INTO TOPIC_PACKS (TOPIC_PACK_ID, COUNT, CREATE_DATE, NAME, USER_ID) VALUES (nextval('TOPICPACK_SEQ'), 2, '09/11/2016 18:19', 'TopicPack2', 1);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '01/11/2016 18:19', 'So, you decided that you are interested in using a database in your web development, huh? Well, then the next step would be to learn about how the database works.', 'How to Create a Database: The Basics', 'How the data is stored in a database is probably much simpler than you might think. Databases use a series of Tables to store the data. A table simply refers to a two dimensional representation of your data using columns and rows. So, then , how does the database keep things straight? Well, first each database table is given a unique name. Without a unique name the DBMS (DataBase Management System) would get very confused. Next, each column in the table is given a unique name. In our example above it would be something like first_name, last_name, email. This doesnt mean each column that you name has to be unique within the entire database. It only has to be unique within the table you have created. Also notice that the names do not use any spaces. When naming tables and columns be sure to keep it simple with letters and numbers. Spaces and symbols can be illegal characters that will mess up the whole works, so if you need to clarify a name use the "_" instead of spaces.', 1, 4.2, 10);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '02/11/2016 18:19', 'Global warming is real and everybody should know about it.', 'Global Warming', 'What is Global Warming? Global Warming is the increase of Earths average surface temperature due to effect of greenhouse gases, such as carbon dioxide emissions from burning fossil fuels or from deforestation, which trap heat that would otherwise escape from Earth. This is a type of greenhouse effect. Is global warming, caused by human activity, even remotely plausible? Earths climate is mostly influenced by the first 6 miles or so of the atmosphere which contains most of the matter making up the atmosphere. This is really a very thin layer if you think about it. In the book The End of Nature, author Bill McKibbin tells of walking three miles to from his cabin in the Adirondacks to buy food. Afterwards, he realized that on this short journey he had traveled a distance equal to that of the layer of the atmosphere where almost all the action of our climate is contained. In fact, if you were to view Earth from space, the principle part of the atmosphere would only be about as thick as the skin on an onion! Realizing this makes it more plausible to suppose that human beings can change the climate. A look at the amount of greenhouse gases we are spewing into the atmosphere (see below), makes it even more plausible.', 2, 3.6, 5);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '03/11/2016 18:19', 'Who is a sofware engineer? What do software engineers do?', 'Software Engineer', 'A software engineer is a person who applies the principles of software engineering to the design, development, maintenance, testing, and evaluation of the software and systems that make computers or anything containing software work.', 3, 1.3, 30);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '04/11/2016 18:19', 'Meditation is real. Meditation can help. Meditation is here.', 'Meditation', 'Meditation is a means of transforming the mind. Buddhist meditation practices are techniques that encourage and develop concentration, clarity, emotional positivity, and a calm seeing of the true nature of things. By engaging with a particular meditation practice you learn the patterns and habits of your mind, and the practice offers a means to cultivate new, more positive ways of being. With regular work and patience these nourishing, focused states of mind can deepen into profoundly peaceful and energised states of mind. Such experiences can have a transformative effect and can lead to a new understanding of life.', 5, 2.4, 50);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '05/11/2016 18:19', 'Running is OK but HIIT is the shit.', 'H.I.I.T.', 'High-intensity interval training (HIIT), also called high-intensity intermittent exercise (HIIE) or sprint interval training (SIT), is a form of interval training, an exercise strategy alternating short periods of intense anaerobic exercise with less-intense recovery periods. HIIT is a form of cardiovascular exercise. Usual HIIT sessions may vary from 4 to 30 minutes. These short, intense workouts provide improved athletic capacity and condition as well as improved glucose metabolism.[1] Compared with other regimens, HIIT may not be as effective for treating hyperlipidemia and obesity, or improving muscle and bone mass. Some researchers also note that HIIT requires "an extremely high level of subject motivation"[attribution needed] and question whether the general population could safely or practically tolerate the extreme nature of the exercise regimen.', 4, 4.5, 90);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '06/11/2016 18:19', 'Painting is a great way to deal with stress but you need to know the basics first.', 'How to Hold a Brush', 'Pass the pencil. Hold your brush like you are passing someone a pencil or the non-business end of a pair of scissors but keep the brush in your hand, you will need it. This is a looser grip than the Pinch with pressure from your thumb holding the body of the brush against your index and middle finger.', 6, 4.9, 10);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '07/11/2016 18:19', 'You have a drivers license? You need to learn how to parallel park.', 'How to Parallel Park', 'Parallel parking is a method of parking a vehicle parallel to the road , in line with other parked vehicles. Parallel parking usually requires initially driving slightly past the parking space, parallel to the parked vehicle in front of that space, keeping a safe distance, then followed by reversing into that space. Subsequent position adjustment may require the use of forward and reverse gears.', 7, 3, 10);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '08/11/2016 18:19', 'Sharks are more important to the ecosystem than you think.', 'Shark Population', 'Experts estimate that 100 million sharks are killed by people each year. Sharks are eaten as seafood in many areas, including Japan and Australia. Commercial and recreational fishers are believed to be responsible for the reduced populations of some shark species.', 8, 1, 3);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 1, 1, '09/11/2016 18:19', 'Future is now. Embedded systems make it possible.', 'Embedded Systems', 'An embedded system is a computer system with a dedicated function within a larger mechanical or electrical system, often with real-time computing constraints. It is embedded as part of a complete device often including hardware and mechanical parts. Embedded systems control many devices in common use today.', 9, 2, 4);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 2, 1, '09/11/2016 18:20', 'If you devote your time into a game, you better git gud pleb.', 'How to Aim Better in CS GO', 'Being good at aiming is pretty much half of what makes you a better CS GO Player. The other half is game sense, map knowledge and team play. Even though we have already talked a bit about aiming in the How to train CS:GO article, we want to dive much deeper into this topic now. Aiming is a deep and complex subject, so even this will only be an overview containing the most important elements and best practices.', 1, 1.2, 5);
INSERT INTO TOPICS (TOPIC_ID, TOPIC_PACK_ID, USER_ID, CREATE_DATE, DESCRIPTION, HEADER, CONTENT, ORDER_BY, RATE, RATE_COUNTER) VALUES (nextval('TOPICS_SEQ'), 2, 1, '09/11/2016 18:21', 'Is SKYNET here? Should we be concerned about machines that can learn? Let us start with a definition.', 'What is Machine Learning', 'Machine learning is a type of artificial intelligence (AI) that provides computers with the ability to learn without being explicitly programmed. Machine learning focuses on the development of computer programs that can teach themselves to grow and change when exposed to new data.', 2, 1.3, 10);

INSERT INTO TAGS(TAG_ID, LABEL, CATEGORY, REF_COUNT) VALUES (nextval('TAG_SEQ'), 'DBMS', 'abbrevation', 1);
INSERT INTO TAGS(TAG_ID, LABEL, CATEGORY, REF_COUNT) VALUES (nextval('TAG_SEQ'), 'SQL', 'Database', 1);
INSERT INTO TAGS(TAG_ID, LABEL, CATEGORY, REF_COUNT) VALUES (nextval('TAG_SEQ'), 'apple', 'fruit of the apple tree', 1);
INSERT INTO TAGS(TAG_ID, LABEL, CATEGORY, REF_COUNT) VALUES (nextval('TAG_SEQ'), 'orange', 'fruit of the Citrus tree', 1);

INSERT INTO TOPIC_TAGS(TOPIC_ID, TAG_ID) VALUES (1, 1);
INSERT INTO TOPIC_TAGS(TOPIC_ID, TAG_ID) VALUES (1, 2);
INSERT INTO TOPIC_TAGS(TOPIC_ID, TAG_ID) VALUES (1, 3);
INSERT INTO TOPIC_TAGS(TOPIC_ID, TAG_ID) VALUES (2, 4);


INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 1, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 2, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 3, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 4, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 5, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 6, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 7, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 8, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 9, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 10, '12/12/2016 00:00:00', 'ilkquiz');
INSERT INTO QUIZES (QUIZ_ID, USER_ID, TOPIC_ID, CREATE_DATE, NAME) VALUES (nextval('QUIZ_SEQ'), 1, 11, '12/12/2016 00:00:00', 'ilkquiz');

INSERT INTO QUESTIONS (QUESTION_ID, QUIZ_ID, QUESTION, CHOICEA, CHOICEB, CHOICEC, CHOICED, right_answer, DATE_CREATED) VALUES (nextval('QUESTION_SEQ'), 1, 'naber', 'iyi', 'idare eder', 'asd', 'acasd', 'A', '01/01/2016');
INSERT INTO QUESTIONS (QUESTION_ID, QUIZ_ID, QUESTION, CHOICEA, CHOICEB, CHOICEC, CHOICED, right_answer, DATE_CREATED) VALUES (nextval('QUESTION_SEQ'), 1, 'soru 2', 'idsadayi', 'idarsaddase eder', 'asd', 'acasaddad', 'B', '01/01/2016');