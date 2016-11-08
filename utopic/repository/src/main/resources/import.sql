# noinspection SqlNoDataSourceInspectionForFile
INSERT INTO users (id, email, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (nextval('USERS_SEQ'), 'ugur@admin.com', true, 'Ugur', 'm', NULL, 'Bor', NULL, '$2a$10$xHrHghoH88RS1OZ7ovl1fO22Zmz4RmwETKrQe.SLz9hJQsiHZ4acy', 'ugurbor');
INSERT INTO users (id, email, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (nextval('USERS_SEQ'), 'one@admin.com', true, 'one', 'm', NULL, 'one', NULL, '$2a$10$OIgL3cS5enwzHk9WtFGp7uaQ085JTvxdrhiD.6n98SpHsmhdgHouK', 'userone');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_EXPLORER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_CREATOR');
INSERT INTO AUTHORITY (ID, NAME) VALUES (3, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (nextval('AUTH_SEQ'), 1, 1);
INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (nextval('AUTH_SEQ'), 1, 2);
INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (nextval('AUTH_SEQ'), 1, 3);
INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (nextval('AUTH_SEQ'), 2, 1);