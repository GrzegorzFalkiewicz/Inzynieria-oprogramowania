DROP TABLE IF EXISTS book_library;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS library;
DROP TABLE IF EXISTS author;

DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;

CREATE TABLE author
(
    id        INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE library
(
    id   INT NOT NULL AUTO_INCREMENT,
    logo VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book
(
    id        INT NOT NULL AUTO_INCREMENT,
    title     VARCHAR(255) NOT NULL,
    cover     VARCHAR(255) NOT NULL,
    rating    FLOAT DEFAULT NULL,
    author_id INT DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_book_author
        FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE book_library
(
    book_id    INT NOT NULL,
    library_id INT NOT NULL,
    PRIMARY KEY (book_id, library_id),
    CONSTRAINT fk_booklibrary_book
        FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT fk_booklibrary_library
        FOREIGN KEY (library_id) REFERENCES library(id)
);

INSERT INTO library(id, logo, name)
VALUES (1, 'https://example.com/logos/bsl.png', 'Biblioteka Slaska');
INSERT INTO library(id, logo, name)
VALUES (2, 'https://example.com/logos/bj.png', 'Biblioteka Jagiellonska');
INSERT INTO library(id, logo, name)
VALUES (3, 'https://example.com/logos/bn.png', 'Biblioteka Narodowa');
INSERT INTO library(id, logo, name)
VALUES (4, 'https://example.com/logos/bm.png', 'Biblioteka Miejska');

INSERT INTO author(id, firstname, lastname)
VALUES (1, 'Adam', 'Mickiewicz');
INSERT INTO author(id, firstname, lastname)
VALUES (2, 'Henryk', 'Sienkiewicz');
INSERT INTO author(id, firstname, lastname)
VALUES (3, 'Boleslaw', 'Prus');

INSERT INTO book(id, title, cover, rating, author_id)
VALUES (1, 'Pan Tadeusz', 'https://example.com/covers/pan-tadeusz.jpg', 8.5, 1);
INSERT INTO book(id, title, cover, rating, author_id)
VALUES (2, 'Dziady', 'https://example.com/covers/dziady.jpg', 8.0, 1);
INSERT INTO book(id, title, cover, rating, author_id)
VALUES (3, 'Quo Vadis', 'https://example.com/covers/quo-vadis.jpg', 8.7, 2);
INSERT INTO book(id, title, cover, rating, author_id)
VALUES (4, 'Krzyzacy', 'https://example.com/covers/krzyzacy.jpg', 8.2, 2);
INSERT INTO book(id, title, cover, rating, author_id)
VALUES (5, 'Lalka', 'https://example.com/covers/lalka.jpg', 9.1, 3);
INSERT INTO book(id, title, cover, rating, author_id)
VALUES (6, 'Faraon', 'https://example.com/covers/faraon.jpg', 8.4, 3);

INSERT INTO book_library(book_id, library_id)
VALUES (1, 1);
INSERT INTO book_library(book_id, library_id)
VALUES (1, 2);
INSERT INTO book_library(book_id, library_id)
VALUES (2, 1);
INSERT INTO book_library(book_id, library_id)
VALUES (3, 2);
INSERT INTO book_library(book_id, library_id)
VALUES (3, 3);
INSERT INTO book_library(book_id, library_id)
VALUES (4, 3);
INSERT INTO book_library(book_id, library_id)
VALUES (5, 1);
INSERT INTO book_library(book_id, library_id)
VALUES (5, 4);
INSERT INTO book_library(book_id, library_id)
VALUES (6, 2);
INSERT INTO book_library(book_id, library_id)
VALUES (6, 4);

CREATE TABLE user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE role
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    role     VARCHAR(255)
);

INSERT INTO user(username, password)
VALUES ('dbuser1', '$2a$10$eiA5dKnoUk77EKXZhJvq7O3XBy5ECYupA0FCEm0gS58QSY6PoPcOS'),
       ('dbuser2', '$2a$10$eiA5dKnoUk77EKXZhJvq7O3XBy5ECYupA0FCEm0gS58QSY6PoPcOS'),
       ('dbuser3', '$2a$10$eiA5dKnoUk77EKXZhJvq7O3XBy5ECYupA0FCEm0gS58QSY6PoPcOS');

INSERT INTO role(username, role)
VALUES ('dbuser1', 'USER_ADMIN'),
       ('dbuser2', 'AUTHOR_ADMIN'),
       ('dbuser3', 'BOOK_ADMIN');