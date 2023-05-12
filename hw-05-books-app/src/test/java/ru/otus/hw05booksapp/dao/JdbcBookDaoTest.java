package ru.otus.hw05booksapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.hw05booksapp.dao.impl.JdbcBookDao;
import ru.otus.hw05booksapp.entity.Author;
import ru.otus.hw05booksapp.entity.Book;
import ru.otus.hw05booksapp.entity.Genre;
import ru.otus.hw05booksapp.exception.DaoException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import(JdbcBookDao.class)
class JdbcBookDaoTest {

    private final int EXPECTED_BOOKS_COUNT = 10;

    private final Author AUTHOR_ONE = new Author(1, "author1");
    private final Author AUTHOR_UPDATED = new Author(2, "author2");
    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "genre1");
    private final Genre GENRE_UPDATED = new Genre(2, "genre2");
    private final String BOOK_ONE_TITLE = "book1";
    private final String BOOK_ONE_TITLE_UPDATED = "book1 UPDATED";

    private final Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_TITLE);
    private final Book BOOK_ONE_UPDATED = new Book(1, AUTHOR_UPDATED, GENRE_UPDATED, BOOK_ONE_TITLE_UPDATED);
    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_TITLE_UPDATED);


    @Autowired
    private BookDao bookDao;

    @Test
    void getCountTest() {
        int actualBooksCount = bookDao.getCount();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }

    @Test
    void findByIdTest() {
        assertEquals(BOOK_ONE, bookDao.findById(1));
    }

    @Test
    void updateByIdTest() {
        bookDao.updateById(BOOK_ONE_UPDATED);
        assertEquals(BOOK_ONE_UPDATED, bookDao.findById(1));
        bookDao.updateById(BOOK_ONE);
        assertEquals(BOOK_ONE, bookDao.findById(1));
    }

    @Test
    void updateTitleByIdTest() {
        bookDao.updateTitleById(1, BOOK_ONE_TITLE_UPDATED);
        assertEquals(BOOK_ONE_TITLE_UPDATED, bookDao.findById(1).getTitle());
        bookDao.updateTitleById(1, BOOK_ONE_TITLE);
        assertEquals(BOOK_ONE_TITLE, bookDao.findById(1).getTitle());
    }

    @Test
    void deleteByIdTest() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());

        bookDao.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.findById(1));
    }

    @Test
    void insertTest() {
        assertThatCode(() -> bookDao.insert(BOOK_CANT_BE_INSERTED))
                .isInstanceOf(DaoException.class);
    }

}