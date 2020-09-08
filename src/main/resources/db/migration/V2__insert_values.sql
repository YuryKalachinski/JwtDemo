INSERT INTO usr (id, login, password)
    VALUES (1, 'admin', '123'), (2, 'user', '123');

INSERT INTO user_role (user_id, role)
    VALUES (1, 'ADMIN'), (1, 'USER'), (2, 'USER');

ALTER SEQUENCE user_sequence RESTART WITH 3;