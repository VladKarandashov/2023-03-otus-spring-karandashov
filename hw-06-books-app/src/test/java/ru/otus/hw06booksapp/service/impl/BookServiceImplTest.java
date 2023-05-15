package ru.otus.hw06booksapp.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06booksapp.entity.Author;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.entity.Note;
import ru.otus.hw06booksapp.repository.jpa.JpaBookRepository;
import ru.otus.hw06booksapp.repository.jpa.JpaNoteRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA books repository testing.")
@DataJpaTest
@Import({BookServiceImpl.class, JpaBookRepository.class, JpaNoteRepository.class})
class BookServiceImplTest {

    private final static int EXPECTED_BOOKS_COUNT = 10;
    private final static Author AUTHOR_ONE = new Author(1L, "Михаил Булгаков");
    private final static Author AUTHOR_NOT_EXIST = new Author(100L, "Author not exist");
    private final static Genre GENRE_ONE = new Genre(1L, "Roman");
    private final static String BOOK_ONE_NAME = "MasterTest";
    private final static String BOOK_ONE_NAME_UPDATED = "MasterTest - NEW";
    private final static Book BOOK_ONE = new Book(1L, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final static List<Note> BOOK_ONE_RIEVIEWS = Arrays.asList(new Note(1L, BOOK_ONE, "Note-01.1 - Master"), new Note(2L, BOOK_ONE, "Note-01.2 - Мастер"));
    private final static Book BOOK_CANT_BE_INSERTED = new Book(10L, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);


    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Book book = bookService.getBookById(1L);
        assertThat(book).isNotNull()
                .matches(b -> b.getId() == BOOK_ONE.getId())
                .matches(b -> b.getTitle().equals(BOOK_ONE.getTitle()));
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        List<Book> books = bookService.getAllBooks();
        assertEquals(EXPECTED_BOOKS_COUNT, books.size());
    }

    @DisplayName("Should get correct books count")
    @Test
    void ShouldGetCorrectBooksCount() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookService.getBooksCount());
    }

    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookService.getBookById(1L);
        assertEquals(BOOK_ONE_NAME, book.getTitle());
        // DELETE:
        bookService.deleteBook(1L);
        assertThatCode(() -> bookService.getBookById(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("Should be able to insert new book-1")
    @Test
    void shouldInsertNewBook() {
        Book book = new Book(null, new Author(1L, "Михаил Булгаков"), new Genre(1L, "Roman"), "MasterTest");
        Book savedBook = bookService.saveBook(book);
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getGenre(), savedBook.getGenre());
        assertEquals(book.getTitle(), savedBook.getTitle());
    }

}