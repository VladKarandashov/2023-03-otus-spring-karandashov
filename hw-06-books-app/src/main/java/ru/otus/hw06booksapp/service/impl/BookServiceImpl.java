package ru.otus.hw06booksapp.service.impl;

import jakarta.persistence.TransactionRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.entity.Note;
import ru.otus.hw06booksapp.exception.DaoException;
import ru.otus.hw06booksapp.exception.NotFoundException;
import ru.otus.hw06booksapp.repository.BookRepository;
import ru.otus.hw06booksapp.repository.NoteRepository;
import ru.otus.hw06booksapp.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {
        Book book = bookRepository.getBookById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        return book;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Note> getNoteByBookId(Long bookId) {
        return noteRepository.getNoteByBookId(bookId);
    }


    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        return bookRepository.getBooksCount();
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.getBookById(id).ifPresent(bookRepository::deleteBook);
    }


    @Transactional
    @Override
    public Book saveBook(Book book) {
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        try {
            return bookRepository.saveBook(book);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Book not found", e);
        } catch (TransactionRequiredException e) {
            throw new DaoException("Transaction exception during book insertion.", e);
        }
    }

    @Transactional
    @Override
    public Book saveBook(Long id, String newTitle) {
        Book book = bookRepository.getBookById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        book.setTitle(newTitle);
        return saveBook(book);
    }


}