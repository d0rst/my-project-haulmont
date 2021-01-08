INSERT INTO authors (firstname, lastname, patronymic) VALUES
('Stebunov', 'Ilya', 'Vitalievich'),
('famil', 'imya', 'otch'),
('famil2', 'imya2', 'otch2');

INSERT INTO genres (name) VALUES ('genre1');
INSERT INTO genres (name) VALUES ('genre2');
INSERT INTO genres (name) VALUES ('genre3');

INSERT INTO books (book_id, author_id, genre_id, city, publisher, title, year) VALUES
(0, 0, 0, 'city1', 'MOSCOW', 'book1', 2005),
(1, 1, 1, 'city2', 'SAINT_PETERSBURG', 'book2', 2006),
(2, 2, 2, 'city2', 'O_REILLY', 'book3', 2007);


