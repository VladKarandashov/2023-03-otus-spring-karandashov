package ru.otus.hw09booksapp.service.impl;

import jakarta.persistence.TransactionRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09booksapp.dto.BookCompleteDto;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.entity.Book;
import ru.otus.hw09booksapp.exception.DaoException;
import ru.otus.hw09booksapp.exception.NotFoundException;
import ru.otus.hw09booksapp.repository.BookRepository;
import ru.otus.hw09booksapp.repository.NoteRepository;
import ru.otus.hw09booksapp.service.AuthorService;
import ru.otus.hw09booksapp.service.BookService;
import ru.otus.hw09booksapp.service.GenreService;
import ru.otus.hw09booksapp.utils.DtoConverter;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public BookDto getById(long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        return DtoConverter.getBookDto(book);
    }

    @Override
    public BookCompleteDto getCompleteById(long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        var author = book.getAuthor();
        var genre = book.getGenre();
        return new BookCompleteDto(
                book.getId(),
                book.getTitle(),
                DtoConverter.getAuthorDto(author),
                DtoConverter.getGenreDto(genre));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(DtoConverter::getBookDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {
        return bookRepository.count();
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
    public BookDto create(BookDto bookDto) {
        var author = authorService.getByName(bookDto.getAuthor());
        var genre = genreService.getByName(bookDto.getGenre());
        Book book = new Book(null, author, genre, bookDto.getTitle());
        return DtoConverter.getBookDto(update(book));
    }

    @Transactional
    @Override
    public BookDto update(BookDto bookDto) {
        var author = authorService.getByName(bookDto.getAuthor());
        var genre = genreService.getByName(bookDto.getGenre());
        Book book = new Book(bookDto.getId(), author, genre, bookDto.getTitle());
        var saveBook = update(book);
        return DtoConverter.getBookDto(saveBook);
    }

    @Transactional
    @Override
    public BookDto update(BookCompleteDto bookDto) {
        var author = authorService.getById(bookDto.getAuthor().getId());
        var genre = genreService.getById(bookDto.getGenre().getId());
        Book book = new Book(bookDto.getId(), author, genre, bookDto.getTitle());
        var saveBook = update(book);
        return DtoConverter.getBookDto(saveBook);
    }

    @Transactional
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
}