
CREATE TABLE author(
    author_id BIGINT AUTO_INCREMENT,
    name CHARACTER VARYING(255),
    CONSTRAINT author_pk PRIMARY KEY (author_id)
);


CREATE TABLE genre(
    genre_id BIGINT AUTO_INCREMENT,
    title CHARACTER VARYING(50),
    CONSTRAINT genre_pk PRIMARY KEY (genre_id)
);


CREATE TABLE book(
    book_id BIGINT AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    title CHARACTER VARYING(50),
    CONSTRAINT book_pk PRIMARY KEY (book_id)
);

ALTER TABLE book ADD CONSTRAINT book_fk_01 FOREIGN KEY (author_id) REFERENCES author (author_id);
ALTER TABLE book ADD CONSTRAINT book_fk_02 FOREIGN KEY (genre_id)  REFERENCES genre  (genre_id);