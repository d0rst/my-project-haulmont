CREATE TABLE authors (
author_id BIGINT IDENTITY PRIMARY KEY,
firstname VARCHAR(255),
lastname VARCHAR(255),
patronymic VARCHAR(255)
);

CREATE TABLE genres (
genre_id BIGINT IDENTITY PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE books (
book_id BIGINT IDENTITY PRIMARY KEY,
city VARCHAR(255),
publisher VARCHAR(255),
title VARCHAR(255),
year INTEGER,
author_id BIGINT,
genre_id BIGINT,
FOREIGN KEY (author_id) REFERENCES authors(author_id),
FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);


