INSERT INTO authors (firstname, lastname, patronymic) VALUES
('Stebunov', 'Ilya', 'Vitalievich'),
('famil', 'imya', 'otch'),
('famil2', 'imya2', 'otch2');

INSERT INTO genres (name) VALUES
('genre1'),
('genre2'),
('genre3');

INSERT INTO books ( city, publisher, title, year) VALUES
('city1', 'Москва', 'book1', 2005),
('city2', 'Санкт-Петербург', 'book2', 2006),
('city2', 'O’Reilly', 'book3', 2007);

INSERT INTO GENRE_BOOK (GENRE_ID, BOOK_ID) VALUES
(0, 0),
(0, 1),
(0, 2),
(1, 0),
(1, 1),
(2, 0);


