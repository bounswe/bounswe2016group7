# noinspection SqlNoDataSourceInspectionForFile
INSERT INTO users (id, email, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (1, 'ugur@admin.com', true, 'Ugur', 'm', NULL, 'Bor', NULL, '$2a$10$xHrHghoH88RS1OZ7ovl1fO22Zmz4RmwETKrQe.SLz9hJQsiHZ4acy', 'ugurbor');
INSERT INTO users (id, email, enabled, firstname, gender, last_password_reset_date, lastname, middlename, password, username) VALUES (2, 'one@admin.com', true, 'one', 'm', NULL, 'one', NULL, '$2a$10$OIgL3cS5enwzHk9WtFGp7uaQ085JTvxdrhiD.6n98SpHsmhdgHouK', 'userone');

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (1, 1, 1);
INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (2, 1, 2);
INSERT INTO USER_AUTHORITY (RECORD_ID, USER_ID, AUTHORITY_ID) VALUES (3, 2, 1);