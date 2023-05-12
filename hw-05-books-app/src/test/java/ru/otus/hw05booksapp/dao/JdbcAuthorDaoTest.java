package ru.otus.hw05booksapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05booksapp.dao.impl.JdbcAuthorDao;
import ru.otus.hw05booksapp.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(JdbcAuthorDao.class)
public class JdbcAuthorDaoTest {

    private final Author AUTHOR_ONE = new Author(1, "author1");

    @Autowired
    AuthorDao authorDao;

    @Test
    void findByIdTest() {
        assertEquals(AUTHOR_ONE, authorDao.findById(AUTHOR_ONE.getId()));
    }

    @Test
    void findByNameTest() {
        assertEquals(AUTHOR_ONE, authorDao.findByName(AUTHOR_ONE.getName()));
    }
}
