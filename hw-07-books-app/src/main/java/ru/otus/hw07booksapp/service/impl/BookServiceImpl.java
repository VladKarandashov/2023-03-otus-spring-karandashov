package ru.otus.hw07booksapp.service.impl;

import jakarta.persistence.TransactionRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07booksapp.dto.BookDto;
import ru.otus.hw07booksapp.entity.Book;
import ru.otus.hw07booksapp.exception.DaoException;
import ru.otus.hw07booksapp.exception.NotFoundException;
import ru.otus.hw07booksapp.repository.BookRepository;
import ru.otus.hw07booksapp.service.AuthorService;
import ru.otus.hw07booksapp.service.BookService;
import ru.otus.hw07booksapp.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        return bookRepository.countBy();
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        if (book == null) {
            throw new NotFoundException(BOOK_NOT_EXIST);
        }
        try {
            return bookRepository.save(book);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(BOOK_NOT_EXIST, e);
        } catch (TransactionRequiredException e) {
            throw new DaoException("Transaction exception during book insertion.", e);
        }
    }

    @Transactional
    @Override
    public Book createBook(BookDto bookDto) {
        var author = authorService.getById(bookDto.getAuthorId());
        var genre = genreService.getGenreById(bookDto.getGenreId());
        Book book = new Book(null, author, genre, bookDto.getTitle());
        return createBook(book);
    }

    @Transactional
    @Override
    public Book createBook(Long id, String newTitle) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        book.setTitle(newTitle);
        return createBook(book);
    }
}