INSERT INTO ROLE (ROLE_KEY, ROLE_DESCRIPTION)
VALUES ('ADMIN', 'Admin user can view, delete the details of all users and modify their own details');

INSERT INTO ROLE (ROLE_KEY, ROLE_DESCRIPTION)
VALUES ('GENERAL', 'General user can view, delete, modify their own details');


INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('tanmay.ray@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 1);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.one@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.two@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.three@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('jane.four@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.five@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.six@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('jane.seven@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('john.eight@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('jane.nine@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);

INSERT INTO USER_AUTH (EMAIL, PASSWORD, ROLE_ID)
VALUES ('jane.ten@gmail.com', '$2a$10$wMYugw9.tkVPLPzKkjCOUuZFzn/uYhH1TUCyXJNOTzNyQyWSs/bpe', 2);


INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('Tanmay Ray', CAST('1994-03-06' AS DATE), 27, 'Male', 'tanmay.ray@gmail.com', 7782665544);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John One', CAST('1994-04-06' AS DATE), 27, 'Male', 'john.one@gmail.com', 7788665541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Two', CAST('1994-07-20' AS DATE), 27, 'Male', 'john.two@gmail.com', 7788345541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Three', CAST('1994-05-06' AS DATE), 27, 'Male', 'john.three@gmail.com', 7788663441);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Four', CAST('1994-08-06' AS DATE), 27, 'Female', 'jane.four@gmail.com', 7948665541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Five', CAST('1994-01-06' AS DATE), 27, 'Male', 'john.five@gmail.com', 7788105541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Six', CAST('1994-02-06' AS DATE), 27, 'Male', 'john.six@gmail.com', 7788605541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('Jane Seven', CAST('1994-09-06' AS DATE), 27, 'Female', 'jane.seven@gmail.com', 7658665541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('John Eight', CAST('1994-07-26' AS DATE), 27, 'Male', 'john.eight@gmail.com', 9888665541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('Jane Nine', CAST('1994-06-11' AS DATE), 27, 'Female', 'jane.nine@gmail.com', 7657665541);

INSERT INTO USER_DETAIL (NAME, DOB, AGE, GENDER, EMAIL, PHONE)
VALUES ('Jane Ten', CAST('1994-05-09' AS DATE), 27, 'Female', 'jane.ten@gmail.com', 7488665541);