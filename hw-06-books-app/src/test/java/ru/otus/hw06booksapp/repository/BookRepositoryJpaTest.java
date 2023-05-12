package ru.otus.hw06booksapp.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06booksapp.entity.Author;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.entity.Note;
import ru.otus.hw06booksapp.repository.jpa.BookRepositoryJpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA books repository testing.")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private final static int EXPECTED_BOOKS_COUNT = 10;
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final static Genre GENRE_ONE = new Genre(1, "Roman");
    private final static String BOOK_ONE_NAME = "MasterTest";
    private final static String BOOK_ONE_NAME_UPDATED = "MasterTest - NEW";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final static List<Note> BOOK_ONE_RIEVIEWS = Arrays.asList(new Note(1, BOOK_ONE, "Note-01.1 - Master"), new Note(2, BOOK_ONE, "Note-01.2 - Мастер"));
    private final static Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);
    private final static long BOOK_ID = 1l;

    @Autowired
    private BookRepositoryJpa bookRepository;

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Optional<Book> book = bookRepository.getBookById(BOOK_ID);
        assertEquals(BOOK_ONE.getId(), book.get().getId());
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldGetAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        org.assertj.core.api.Assertions.assertThat(books).isNotNull().hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> s.getAuthor().getId() > 0)
                .allMatch(s -> !s.getAuthor().getName().equals(""))
                .allMatch(s -> s.getGenre().getId() > 0)
                .allMatch(s -> !s.getGenre().getName().equals(""))
                .allMatch(s -> !s.getTitle().equals(""));
    }


    @DisplayName("Should get correct books count")
    @Test
    void countShouldGetCorrectNumber() {
        Long genreCount = bookRepository.getBooksCount();
        assertEquals(EXPECTED_BOOKS_COUNT, genreCount);
    }


    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookRepository.getBookById(BOOK_ONE.getId()).get();
        assertEquals(BOOK_ONE_NAME, book.getTitle());
        // DELETE:
        bookRepository.deleteBook(book);
        Optional<Book> bookOptional = bookRepository.getBookById(BOOK_ONE.getId());
        assertEquals(Optional.empty(), bookOptional);
    }


    @DisplayName("Should be able to insert a book-1 after deletions")
    @Test
    void shouldAddNewBook() {
        Book book = new Book(0L, AUTHOR_ONE, GENRE_ONE, "MasterTest");
        Book savedBook = bookRepository.saveBook(book);
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getGenre(), savedBook.getGenre());
        assertEquals(book.getTitle(), savedBook.getTitle());
    }

}