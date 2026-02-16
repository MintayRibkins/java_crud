CREATE SCHEMA IF NOT EXISTS test;
SET SCHEMA test;

-- DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(80),
    surname VARCHAR(80),
    email   VARCHAR(80),
    address VARCHAR(255)
);

-- INSERT INTO users (name, surname, email, address)
-- VALUES ('John', 'Doe', '', '123 Entity.User St');
