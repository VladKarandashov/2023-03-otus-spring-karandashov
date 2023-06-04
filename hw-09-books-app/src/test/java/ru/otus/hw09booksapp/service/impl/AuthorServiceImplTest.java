package ru.otus.hw09booksapp.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw09booksapp.entity.Author;
import ru.otus.hw09booksapp.service.AuthorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Authors repository testing.")
@DataJpaTest
@Import({AuthorServiceImpl.class})
class AuthorServiceImplTest {

    private final static int EXPECTED_AUTHORS_COUNT = 1;
    private final static long AUTHOR_ONE_ID = 1L;
    private final static String AUTHOR_ONE_NAME = "test";
    private final static String AUTHOR_ONE_NAME_NEW = "Михаил Булгаков - New";

    @Autowired
    private AuthorService authorService;


    @DisplayName("Should get correct Author")
    @Test
    void shouldGetCorrectAuthor() {
        Author author = authorService.getById(AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_ID, author.getId());
        assertEquals(AUTHOR_ONE_NAME, author.getName());
    }

    //pavel_ignatenko@mail.ru

    @DisplayName("Should find all Authors")
    @Test
    void ShouldGetAllAuthors() {
        val authors = authorService.getAll();
        assertThat(authors).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }


    @DisplayName("Should be able to delete a Author:")
    @Test
    void shouldDeletefirstAuthor() {
        Author author = authorService.getById(AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_NAME, author.getName());
        // DELETE:
        authorService.deleteById(author.getId());
        assertThatCode(() -> authorService.getById(AUTHOR_ONE_ID))
                .isInstanceOf(RuntimeException.class);
    }
}