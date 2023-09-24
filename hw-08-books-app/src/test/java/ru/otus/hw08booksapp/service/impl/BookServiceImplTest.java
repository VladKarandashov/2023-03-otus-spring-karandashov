package ru.otus.hw08booksapp.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw08booksapp.dto.BookDto;
import ru.otus.hw08booksapp.entity.Book;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ORM MONGO books repository testing.")
@DataMongoTest
@Import({BookServiceImpl.class,
        AuthorServiceImpl.class})
class BookServiceImplTest {

    private static final String MY_BOOK = "my book";
    private static final String MY_BOOK_1 = "my book 1";
    private static final String MY_BOOK_2 = "my book 2";
    private static final String DEL_BOOK = "del book";
    private static final String AUTHOR = "author";

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        var authorId = authorService.create(AUTHOR);
        var id = bookService.create(new BookDto(MY_BOOK, authorId, null));
        Book book = bookService.getById(id);
        Assertions.assertThat(book).isNotNull()
                .matches(b -> Objects.equals(b.getTitle(), MY_BOOK));
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        var authorId = authorService.create(AUTHOR);
        bookService.create(new BookDto(MY_BOOK_1, authorId, null));
        bookService.create(new BookDto(MY_BOOK_2, authorId, null));
        List<Book> books = bookService.getAll();
        assertTrue(books.stream().anyMatch(book -> MY_BOOK_1.equals(book.getTitle())));
        assertTrue(books.stream().anyMatch(book -> MY_BOOK_2.equals(book.getTitle())));
    }

    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeleteAuthor() {
        var authorId = authorService.create(AUTHOR);
        var id = bookService.create(new BookDto(DEL_BOOK, authorId, null));
        bookService.deleteById(id);
        var books = bookService.getAll();
        assertTrue(books.stream().noneMatch(book -> DEL_BOOK.equals(book.getTitle())));
    }

}