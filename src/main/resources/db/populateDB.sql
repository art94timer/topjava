DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);
    
INSERT INTO meals (id, description, calories, date_time, user_id)
VALUES (100000, 'User', 100, '2022-01-01', 100000),
       (100001, 'Admin', 300, '2022-01-01', 100001),
       (100002, 'Guest', 500, '2022-01-01', 100002);