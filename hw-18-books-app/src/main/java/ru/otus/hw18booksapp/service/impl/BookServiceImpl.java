package ru.otus.hw18booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw18booksapp.dto.BookDto;
import ru.otus.hw18booksapp.dto.request.UpdateRequest;
import ru.otus.hw18booksapp.entity.Book;
import ru.otus.hw18booksapp.exception.NotFoundException;
import ru.otus.hw18booksapp.repository.AuthorRepository;
import ru.otus.hw18booksapp.repository.BookRepository;
import ru.otus.hw18booksapp.repository.GenreRepository;
import ru.otus.hw18booksapp.service.BookService;
import ru.otus.hw18booksapp.utils.DtoConverter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CircuitBreakerFactory<?, ?> cbFactory;

    @Transactional(readOnly = true)
    @Override
    public BookDto getById(long id) {
        return cbFactory.create("getBookById").run(() -> {
            var book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
            return DtoConverter.getBookDto(book);
        }, this::fallbackUndefinedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll() {
        return cbFactory.create("getAllBook").run(() ->
                bookRepository
                        .findAll()
                        .stream()
                        .map(DtoConverter::getBookDto)
                        .toList(),
                this::fallbackUndefinedBooks);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        return cbFactory.create("createBook").run(() -> {
            var author = authorRepository.findByName(bookDto.getAuthor()).orElse(null);
            var genre = genreRepository.findByName(bookDto.getGenre()).orElse(null);
            Book book = DtoConverter.getBook(bookDto.getTitle(), author, genre);
            var updateBook = bookRepository.save(book);
            return DtoConverter.getBookDto(updateBook);
        }, this::fallbackUndefinedBook);
    }

    @Transactional
    @Override
    public BookDto update(UpdateRequest request) {
        return cbFactory.create("updateBook").run(() -> {
            var author = authorRepository.findByName(request.getAuthor()).orElse(null);
            var genre = genreRepository.findByName(request.getGenre()).orElse(null);
            Book book = new Book(request.getId(), author, genre, request.getTitle());
            var saveBook = bookRepository.save(book);
            return DtoConverter.getBookDto(saveBook);
        }, this::fallbackUndefinedBook);
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