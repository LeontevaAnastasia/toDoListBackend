DROP table if exists tasks;
DROP table if exists user_roles;
DROP table if exists users;
DROP SEQUENCE if exists global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE tasks
(
    id                   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id              INTEGER                           NOT NULL,
    header               VARCHAR(50)                       NOT NULL,
    description          TEXT                              NOT NULL,
    is_completed         BOOL                DEFAULT FALSE NOT NULL,
    date_time_complete   TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


DELETE FROM user_roles;
DELETE FROM tasks;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '$2a$10$rRWCsVtQZa3BX.uaeGhspuBnBBpF7Atlpk2IlEIaig2p7nIo2X.8G'),
       ('User2', 'user2@gmail.com', '$2a$10$rRWCsVtQZa3BX.uaeGhspuBnBBpF7Atlpk2IlEIaig2p7nIo2X.8G'),
       ('Admin', 'admin@gmail.com', '$2a$10$hMsux0kFJre4xtnbfDi.luSTHYldjKgCkbZTugINBIfk0Mv.lF6qG');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002);


INSERT INTO tasks (header, description, user_id)
VALUES ('Docker', 'Learn Docker', 100000),
       ('RabbitMQ', 'Make project with rabbitmq', 100001);
