package ru.otus.hw18booksapp.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw18booksapp.dao.BookDao;
import ru.otus.hw18booksapp.dao.repository.AuthorRepository;
import ru.otus.hw18booksapp.dao.repository.GenreRepository;
import ru.otus.hw18booksapp.dto.BookDto;
import ru.otus.hw18booksapp.dto.request.UpdateRequest;
import ru.otus.hw18booksapp.entity.Book;
import ru.otus.hw18booksapp.exception.NotFoundException;
import ru.otus.hw18booksapp.service.BookService;
import ru.otus.hw18booksapp.utils.DtoConverter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@SuppressWarnings("unused") // иначе fallback методы помечаются как неиспользуемые
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookDao bookDao;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "bookCrudApi", fallbackMethod = "fallbackUndefinedBook")
    public BookDto getById(long id) {
        var book = bookDao.findById(id).orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        return DtoConverter.getBookDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "bookCrudApi", fallbackMethod = "fallbackUndefinedBooks")
    public List<BookDto> getAll() {
        return bookDao
                .findAll()
                .stream()
                .map(DtoConverter::getBookDto)
                .toList();
    }

    @Override
    @Transactional
    @RateLimiter(name = "bookCrudApi")
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    @RateLimiter(name = "bookCrudApi", fallbackMethod = "fallbackUndefinedBook")
    @CircuitBreaker(name = "bookCrudApi", fallbackMethod = "fallbackUndefinedBook")
    public BookDto create(BookDto bookDto) {
        var author = authorRepository.findByName(bookDto.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(bookDto.getGenre()).orElse(null);
        Book book = DtoConverter.getBook(bookDto.getTitle(), author, genre);
        var updateBook = bookDao.save(book);
        return DtoConverter.getBookDto(updateBook);
    }

    @Override
    @Transactional
    @RateLimiter(name = "bookCrudApi")
    public void update(UpdateRequest request) {
        var author = authorRepository.findByName(request.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(request.getGenre()).orElse(null);
        Book book = new Book(request.getId(), author, genre, request.getTitle());
        bookDao.save(book);
    }

    private BookDto fallbackUndefinedBook(Throwable throwable) {
        log.error("Произошла ошибка при обращении в БД", throwable);
        return getEmptyBookDto();
    }

    private List<BookDto> fallbackUndefinedBooks(Throwable throwable) {
        log.error("Произошла ошибка при обращении в БД", throwable);
        return List.of(getEmptyBookDto());
    }

    private BookDto getEmptyBookDto() {
        return new BookDto(-1L, "N/A", "N/A", "N/A");
    }
}