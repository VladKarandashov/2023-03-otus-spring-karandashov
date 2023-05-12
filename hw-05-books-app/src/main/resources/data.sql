INSERT INTO author(author_id, name) VALUES (1, 'Михаил Булгаков');
INSERT INTO author(author_id, name) VALUES (2, 'Антуан де Сент-Экзюпери');
INSERT INTO author(author_id, name) VALUES (3, 'Александр Дюма');
INSERT INTO author(author_id, name) VALUES (4, 'Аркадий и Борис Стругацкие');

INSERT INTO genre (genre_id, title) VALUES (1, 'Роман');
INSERT INTO genre (genre_id, title) VALUES (2, 'Проза');
INSERT INTO genre (genre_id, title) VALUES (3, 'Фантастика');
INSERT INTO genre (genre_id, title) VALUES (4, 'Приключения');

INSERT INTO book(author_id, genre_id, title) VALUES (1, 1, 'Мастер и Маргарита');
INSERT INTO book(author_id, genre_id, title) VALUES (1, 2, 'Белая гвардия');
INSERT INTO book(author_id, genre_id, title) VALUES (1, 3, 'Собачье сердце');
INSERT INTO book(author_id, genre_id, title) VALUES (2, 2, 'Маленький принц');
INSERT INTO book(author_id, genre_id, title) VALUES (3, 4, 'Граф Монте-Кристо');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Трудно быть Богом');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Пикник на обочине');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Улитка на склоне');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Обитаемый остров');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Отель у погибшего альпениста');