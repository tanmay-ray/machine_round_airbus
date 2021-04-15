INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('Tanmay Ray', CAST('1994-03-06' AS DATE), 27, 'Male', 'tanmayray94@gmail.com', 7788665544);

INSERT INTO ROLE (ROLE_KEY, ROLE_DESCRIPTION)
VALUES ('ADMIN', 'Admin user can view, delete the details of all users and modify their own details');

INSERT INTO ROLE (ROLE_KEY, ROLE_DESCRIPTION)
VALUES ('GENERAL', 'General user can view, delete, modify their own details');

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('tanmayray94@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 1);