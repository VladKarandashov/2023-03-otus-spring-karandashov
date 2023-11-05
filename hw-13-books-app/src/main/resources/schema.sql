CREATE TABLE author(
    id BIGINT AUTO_INCREMENT,
    name CHARACTER VARYING(255),
    CONSTRAINT author_pk PRIMARY KEY (id)
);

CREATE TABLE genre(
    id BIGINT AUTO_INCREMENT,
    name CHARACTER VARYING(50),
    CONSTRAINT genre_pk PRIMARY KEY (id)
);

CREATE TABLE book(
    id BIGINT AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    title CHARACTER VARYING(50),
    CONSTRAINT book_pk PRIMARY KEY (id)
);
ALTER TABLE book ADD CONSTRAINT book_fk_01 FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE;
ALTER TABLE book ADD CONSTRAINT book_fk_02 FOREIGN KEY (genre_id)  REFERENCES genre  (id) ON DELETE CASCADE;

CREATE TABLE note(
    id BIGINT AUTO_INCREMENT,
    book_id BIGINT NOT NULL,
    note CHARACTER VARYING(255),
    CONSTRAINT note_pk PRIMARY KEY (id)
);
ALTER TABLE note ADD CONSTRAINT book_note_fk_01 FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE;

CREATE TABLE security_role (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    CONSTRAINT role_pk PRIMARY KEY (id)
);

CREATE TABLE security_user (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    password_confirm BOOLEAN,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE security_user_roles (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (id)
);
ALTER TABLE security_user_roles ADD CONSTRAINT user_fk_01 FOREIGN KEY (user_id) REFERENCES security_user(id);
ALTER TABLE security_user_roles ADD CONSTRAINT role_fk_02 FOREIGN KEY (roles_id) REFERENCES security_role(id);