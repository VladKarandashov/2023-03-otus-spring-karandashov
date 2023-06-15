package ru.otus.hw09booksapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.entity.Author;
import ru.otus.hw09booksapp.entity.Book;
import ru.otus.hw09booksapp.entity.Genre;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({BookServiceImpl.class,
        AuthorServiceImpl.class,
        GenreServiceImpl.class})
class BookServiceImplTest {

    private final static int EXPECTED_BOOKS_COUNT = 1;
    private final static Author AUTHOR_ONE = new Author(1L, "Михаил Булгаков");
    private final static Genre GENRE_ONE = new Genre(1L, "Roman");
    private final static String BOOK_ONE_NAME = "MasterTest";
    private final static Book BOOK_ONE = new Book(1L, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        var book = bookService.getById(1L);
        assertThat(book).isNotNull()
                .matches(b -> Objects.equals(b.getId(), BOOK_ONE.getId()))
                .matches(b -> b.getTitle().equals(BOOK_ONE.getTitle()));
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        var books = bookService.getAll();
        assertEquals(EXPECTED_BOOKS_COUNT, books.size());
    }

    @DisplayName("Should get correct books count")
    @Test
    void ShouldGetCorrectBooksCount() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookService.getCount());
    }

    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeletefirstBook() {
        var book = bookService.getById(1L);
        assertEquals(BOOK_ONE_NAME, book.getTitle());
        bookService.deleteById(1L);
        assertThatCode(() -> bookService.getById(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("Should be able to insert new book-1")
    @Test
    void shouldInsertNewBook() {
        Book book = new Book(null, new Author(1L, "test"), new Genre(1L, "Roman"), "MasterTest");
        var savedBook = bookService.create(new BookDto(null, "MasterTest", "test", "Roman"));
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertEquals(book.getAuthor().getName(), savedBook.getAuthor());
        assertEquals(book.getGenre().getName(), savedBook.getGenre());
        assertEquals(book.getTitle(), savedBook.getTitle());
    }

}