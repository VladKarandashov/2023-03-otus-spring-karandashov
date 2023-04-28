package ru.otus.hw05booksapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.hw05booksapp.entity.Author;
import ru.otus.hw05booksapp.entity.Book;
import ru.otus.hw05booksapp.entity.Genre;
import ru.otus.hw05booksapp.exception.NotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "genre1");
    private final String BOOK_ONE_TITLE_UPDATED = "book1 UPDATED";

    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_TITLE_UPDATED);


    @Autowired
    private BookService bookService;

    @Test
    void getCountTest() {
        int actualBooksCount = bookService.getCount();
        assertNotEquals((Integer) null, actualBooksCount);
    }

    @Test
    void findByIdTest() {
        var book = bookService.findById(2);
        assertEquals(2, book.getId());
    }

    @Test
    void updateTitleByIdTest() {
        bookService.updateTitleById(1, BOOK_ONE_TITLE_UPDATED);
        assertEquals(BOOK_ONE_TITLE_UPDATED, bookService.findById(1).getTitle());
        String BOOK_ONE_TITLE = "book1";
        bookService.updateTitleById(1, BOOK_ONE_TITLE);
        assertEquals(BOOK_ONE_TITLE, bookService.findById(1).getTitle());
    }

    @Test
    void deleteByIdTest() {
        int EXPECTED_BOOKS_COUNT = 10;
        assertEquals(EXPECTED_BOOKS_COUNT, bookService.getCount());

        bookService.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () -> bookService.findById(1));
    }

    @Test
    void insertTest() {
        assertThatCode(() -> bookService.insert(BOOK_CANT_BE_INSERTED.getAuthor().getId(),
                BOOK_CANT_BE_INSERTED.getGenre().getId(),
                BOOK_CANT_BE_INSERTED.getTitle()))
                .isInstanceOf(NotFoundException.class);
    }
}
