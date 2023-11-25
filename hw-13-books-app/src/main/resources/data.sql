MERGE INTO author(id, name) VALUES (1, 'Михаил Булгаков');
MERGE INTO author(id, name) VALUES (2, 'Антуан де Сент-Экзюпери');
MERGE INTO author(id, name) VALUES (3, 'Александр Дюма');
MERGE INTO author(id, name) VALUES (4, 'Аркадий и Борис Стругацкие');
ALTER TABLE AUTHOR ALTER COLUMN ID RESTART WITH 5;

MERGE INTO genre (id, name) VALUES (1, 'Роман');
MERGE INTO genre (id, name) VALUES (2, 'Проза');
MERGE INTO genre (id, name) VALUES (3, 'Фантастика');
MERGE INTO genre (id, name) VALUES (4, 'Приключения');
ALTER TABLE GENRE ALTER COLUMN ID RESTART WITH 5;

MERGE INTO book(id, author_id, genre_id, title) VALUES (1, 1, 1, 'Мастер и Маргарита');
MERGE INTO book(id, author_id, genre_id, title) VALUES (2, 1, 2, 'Белая гвардия');
MERGE INTO book(id, author_id, genre_id, title) VALUES (3, 1, 3, 'Собачье сердце');
MERGE INTO book(id, author_id, genre_id, title) VALUES (4, 2, 2, 'Маленький принц');
MERGE INTO book(id, author_id, genre_id, title) VALUES (5, 3, 4, 'Граф Монте-Кристо');
MERGE INTO book(id, author_id, genre_id, title) VALUES (6, 4, 3, 'Трудно быть Богом');
MERGE INTO book(id, author_id, genre_id, title) VALUES (7, 4, 3, 'Пикник на обочине');
MERGE INTO book(id, author_id, genre_id, title) VALUES (8, 4, 3, 'Улитка на склоне');
MERGE INTO book(id, author_id, genre_id, title) VALUES (9, 4, 3, 'Обитаемый остров');
MERGE INTO book(id, author_id, genre_id, title) VALUES (10, 4, 3, 'Отель у погибшего альпениста');
ALTER TABLE BOOK ALTER COLUMN ID RESTART WITH 11;

MERGE INTO note(id, book_id, note) VALUES (1, 1,  'Note-01.1 - Мастер');
MERGE INTO note(id, book_id, note) VALUES (2, 1,  'Note-01.2 - Мастер');
MERGE INTO note(id, book_id, note) VALUES (3, 2,  'Note-02.1 - гвардия');
MERGE INTO note(id, book_id, note) VALUES (4, 2,  'Note-02.2 - гвардия');
MERGE INTO note(id, book_id, note) VALUES (5, 3,  'Note-03.1 - сердце');
MERGE INTO note(id, book_id, note) VALUES (6, 3,  'Note-03.2 - сердце');
MERGE INTO note(id, book_id, note) VALUES (7, 3,  'Note-03.3 - сердце');
MERGE INTO note(id, book_id, note) VALUES (8, 4,  'Note-04.1 - M принц');
MERGE INTO note(id, book_id, note) VALUES (9, 4,  'Note-04.2 - M принц');
MERGE INTO note(id, book_id, note) VALUES (10, 5,  'Note-05.1 - Граф MK');
MERGE INTO note(id, book_id, note) VALUES (11, 5,  'Note-05.2 - Граф MK');
MERGE INTO note(id, book_id, note) VALUES (12, 5,  'Note-05.3 - Граф MK');
MERGE INTO note(id, book_id, note) VALUES (13, 6,  'Note-06.1 - Трудно');
MERGE INTO note(id, book_id, note) VALUES (14, 6,  'Note-06.2 - Трудно');
MERGE INTO note(id, book_id, note) VALUES (15, 7,  'Note-07.1 - Пикник');
MERGE INTO note(id, book_id, note) VALUES (16, 7,  'Note-07.2 - Пикник');
MERGE INTO note(id, book_id, note) VALUES (17, 7,  'Note-07.3 - Пикник');
MERGE INTO note(id, book_id, note) VALUES (18, 8,  'Note-08.1 - Улитка');
MERGE INTO note(id, book_id, note) VALUES (19, 8,  'Note-08.2 - Улитка');
MERGE INTO note(id, book_id, note) VALUES (20, 8,  'Note-08.3 - Улитка');
MERGE INTO note(id, book_id, note) VALUES (21, 8,  'Note-08.4 - Улитка');
MERGE INTO note(id, book_id, note) VALUES (22, 9,  'Note-09.1 - остров');
MERGE INTO note(id, book_id, note) VALUES (23, 9,  'Note-09.1 - остров');
MERGE INTO note(id, book_id, note) VALUES (24, 10, 'Note-10.1 - Отель');
MERGE INTO note(id, book_id, note) VALUES (25, 10, 'Note-10.2 - Отель');
MERGE INTO note(id, book_id, note) VALUES (26, 10, 'Note-10.3 - Отель');
MERGE INTO note(id, book_id, note) VALUES (27, 10, 'Note-10.4 - Отель');
ALTER TABLE NOTE ALTER COLUMN ID RESTART WITH 28;

MERGE INTO security_role(id, name) VALUES (1, 'ROLE_ADMIN');
MERGE INTO security_role(id, name) VALUES (2, 'ROLE_CLIENT');

MERGE INTO security_user(id, username, password, password_confirm) VALUES (1, 'admin', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', true);
MERGE INTO security_user(id, username, password, password_confirm) VALUES (2, 'client', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', true);
MERGE INTO security_user(id, username, password, password_confirm) VALUES (3, 'client2', '$2a$10$snacezW93WwzzKiedbwPZuZIkmkArUiN2LxC0LtgSK8rolEvrf2dC', false);

MERGE INTO security_user_roles(id, user_id, roles_id) VALUES (1, 1, 1);
MERGE INTO security_user_roles(id, user_id, roles_id) VALUES (2, 2, 2);
MERGE INTO security_user_roles(id, user_id, roles_id) VALUES (3, 3, 2);