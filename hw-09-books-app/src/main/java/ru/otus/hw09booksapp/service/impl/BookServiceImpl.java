package ru.otus.hw09booksapp.service.impl;

import jakarta.persistence.TransactionRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.entity.Book;
import ru.otus.hw09booksapp.exception.DaoException;
import ru.otus.hw09booksapp.exception.NotFoundException;
import ru.otus.hw09booksapp.repository.BookRepository;
import ru.otus.hw09booksapp.repository.NoteRepository;
import ru.otus.hw09booksapp.service.AuthorService;
import ru.otus.hw09booksapp.service.BookService;
import ru.otus.hw09booksapp.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public Book getById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllDto() {
        return getAll().stream()
                .map(book -> new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {
        return bookRepository.countBy();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        // удаляем комментарии к книге
        noteRepository.deleteAllByBook_Id(id);
        // удаляем саму книгу
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Book update(Book book) {
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
    public Book create(BookDto bookDto) {
        var author = authorService.getByName(bookDto.getAuthor());
        var genre = genreService.getByName(bookDto.getGenre());
        Book book = new Book(null, author, genre, bookDto.getTitle());
        return update(book);
    }
}