package ru.otus.hw05booksapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05booksapp.dao.impl.GenreDaoJdbc;
import ru.otus.hw05booksapp.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    private final Genre GENRE_ONE = new Genre(1, "genre1");

    @Autowired
    GenreDao genreDao;

    @Test
    void findByIdTest() {
        assertEquals(GENRE_ONE, genreDao.findById(GENRE_ONE.getId()));
    }

    @Test
    void findByNameTest() {
        assertEquals(GENRE_ONE, genreDao.findByTitle(GENRE_ONE.getTitle()));
    }
}
