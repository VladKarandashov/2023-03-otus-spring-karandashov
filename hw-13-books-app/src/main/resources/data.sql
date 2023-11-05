INSERT INTO author(id, name) VALUES (1, 'Михаил Булгаков');
INSERT INTO author(id, name) VALUES (2, 'Антуан де Сент-Экзюпери');
INSERT INTO author(id, name) VALUES (3, 'Александр Дюма');
INSERT INTO author(id, name) VALUES (4, 'Аркадий и Борис Стругацкие');
ALTER TABLE AUTHOR ALTER COLUMN ID RESTART WITH 5;

INSERT INTO genre (id, name) VALUES (1, 'Роман');
INSERT INTO genre (id, name) VALUES (2, 'Проза');
INSERT INTO genre (id, name) VALUES (3, 'Фантастика');
INSERT INTO genre (id, name) VALUES (4, 'Приключения');
ALTER TABLE GENRE ALTER COLUMN ID RESTART WITH 5;

INSERT INTO book(id, author_id, genre_id, title) VALUES (1, 1, 1, 'Мастер и Маргарита');
INSERT INTO book(id, author_id, genre_id, title) VALUES (2, 1, 2, 'Белая гвардия');
INSERT INTO book(id, author_id, genre_id, title) VALUES (3, 1, 3, 'Собачье сердце');
INSERT INTO book(id, author_id, genre_id, title) VALUES (4, 2, 2, 'Маленький принц');
INSERT INTO book(id, author_id, genre_id, title) VALUES (5, 3, 4, 'Граф Монте-Кристо');
INSERT INTO book(id, author_id, genre_id, title) VALUES (6, 4, 3, 'Трудно быть Богом');
INSERT INTO book(id, author_id, genre_id, title) VALUES (7, 4, 3, 'Пикник на обочине');
INSERT INTO book(id, author_id, genre_id, title) VALUES (8, 4, 3, 'Улитка на склоне');
INSERT INTO book(id, author_id, genre_id, title) VALUES (9, 4, 3, 'Обитаемый остров');
INSERT INTO book(id, author_id, genre_id, title) VALUES (10, 4, 3, 'Отель у погибшего альпениста');
ALTER TABLE BOOK ALTER COLUMN ID RESTART WITH 11;

INSERT INTO note(id, book_id, note) VALUES (1, 1,  'Note-01.1 - Мастер');
INSERT INTO note(id, book_id, note) VALUES (2, 1,  'Note-01.2 - Мастер');
INSERT INTO note(id, book_id, note) VALUES (3, 2,  'Note-02.1 - гвардия');
INSERT INTO note(id, book_id, note) VALUES (4, 2,  'Note-02.2 - гвардия');
INSERT INTO note(id, book_id, note) VALUES (5, 3,  'Note-03.1 - сердце');
INSERT INTO note(id, book_id, note) VALUES (6, 3,  'Note-03.2 - сердце');
INSERT INTO note(id, book_id, note) VALUES (7, 3,  'Note-03.3 - сердце');
INSERT INTO note(id, book_id, note) VALUES (8, 4,  'Note-04.1 - M принц');
INSERT INTO note(id, book_id, note) VALUES (9, 4,  'Note-04.2 - M принц');
INSERT INTO note(id, book_id, note) VALUES (10, 5,  'Note-05.1 - Граф MK');
INSERT INTO note(id, book_id, note) VALUES (11, 5,  'Note-05.2 - Граф MK');
INSERT INTO note(id, book_id, note) VALUES (12, 5,  'Note-05.3 - Граф MK');
INSERT INTO note(id, book_id, note) VALUES (13, 6,  'Note-06.1 - Трудно');
INSERT INTO note(id, book_id, note) VALUES (14, 6,  'Note-06.2 - Трудно');
INSERT INTO note(id, book_id, note) VALUES (15, 7,  'Note-07.1 - Пикник');
INSERT INTO note(id, book_id, note) VALUES (16, 7,  'Note-07.2 - Пикник');
INSERT INTO note(id, book_id, note) VALUES (17, 7,  'Note-07.3 - Пикник');
INSERT INTO note(id, book_id, note) VALUES (18, 8,  'Note-08.1 - Улитка');
INSERT INTO note(id, book_id, note) VALUES (19, 8,  'Note-08.2 - Улитка');
INSERT INTO note(id, book_id, note) VALUES (20, 8,  'Note-08.3 - Улитка');
INSERT INTO note(id, book_id, note) VALUES (21, 8,  'Note-08.4 - Улитка');
INSERT INTO note(id, book_id, note) VALUES (22, 9,  'Note-09.1 - остров');
INSERT INTO note(id, book_id, note) VALUES (23, 9,  'Note-09.1 - остров');
INSERT INTO note(id, book_id, note) VALUES (24, 10, 'Note-10.1 - Отель');
INSERT INTO note(id, book_id, note) VALUES (25, 10, 'Note-10.2 - Отель');
INSERT INTO note(id, book_id, note) VALUES (26, 10, 'Note-10.3 - Отель');
INSERT INTO note(id, book_id, note) VALUES (27, 10, 'Note-10.4 - Отель');
ALTER TABLE NOTE ALTER COLUMN ID RESTART WITH 28;

INSERT INTO security_role(id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO security_role(id, name) VALUES (2, 'ROLE_CLIENT');

INSERT INTO security_user(id, username, password, password_confirm) VALUES (1, 'admin', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', true);
INSERT INTO security_user(id, username, password, password_confirm) VALUES (2, 'client', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', true);
INSERT INTO security_user(id, username, password, password_confirm) VALUES (3, 'client2', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', false);

INSERT INTO security_user_roles(id, user_id, roles_id) VALUES (1, 1, 1);
INSERT INTO security_user_roles(id, user_id, roles_id) VALUES (2, 2, 2);
INSERT INTO security_user_roles(id, user_id, roles_id) VALUES (3, 3, 2);