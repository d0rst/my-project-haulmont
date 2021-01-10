INSERT INTO authors (firstname, lastname, patronymic) VALUES
('Stebunov', 'Ilya', 'Vitalievich'),
('famil', 'imya', 'otch'),
('famil2', 'imya2', 'otch2');

INSERT INTO genres (name) VALUES ('genre1');
INSERT INTO genres (name) VALUES ('genre2');
INSERT INTO genres (name) VALUES ('genre3');

INSERT INTO books (author_id, genre_id, city, publisher, title, year) VALUES
(0, 0, 'city1', 'Москва', 'book1', 2005),
(1, 1, 'city2', 'Санкт-Петербург', 'book2', 2006),
(2, 2, 'city2', 'O’Reilly', 'book3', 2007);


