package ru.otus.hw08booksapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw08booksapp.entity.Author;
import ru.otus.hw08booksapp.service.AuthorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ORM JPA Authors repository testing.")
@DataMongoTest
@Import({AuthorServiceImpl.class})
class AuthorServiceImplTest {

    private static final String MY_AUTHOR = "my author";
    private static final String MY_AUTHOR_1 = "my author 1";
    private static final String MY_AUTHOR_2 = "my author 2";
    private static final String DEL_AUTHOR = "del author";

    @Autowired
    private AuthorService authorService;

    @DisplayName("Should get correct Author")
    @Test
    void shouldGetCorrectAuthor() {
        var id = authorService.create(MY_AUTHOR);
        Author author = authorService.getById(id);
        assertEquals(MY_AUTHOR, author.getName());
    }

    @DisplayName("Should find all Authors")
    @Test
    void ShouldGetAllAuthors() {
        authorService.create(MY_AUTHOR_1);
        authorService.create(MY_AUTHOR_2);
        var authors = authorService.getAll();
        assertTrue(authors.stream().anyMatch(author -> MY_AUTHOR_1.equals(author.getName())));
        assertTrue(authors.stream().anyMatch(author -> MY_AUTHOR_2.equals(author.getName())));
    }


    @DisplayName("Should be able to delete a Author:")
    @Test
    void shouldDeleteAuthor() {
        var id = authorService.create(DEL_AUTHOR);
        authorService.deleteById(id);
        var authors = authorService.getAll();
        assertTrue(authors.stream().noneMatch(author -> DEL_AUTHOR.equals(author.getName())));
    }
}