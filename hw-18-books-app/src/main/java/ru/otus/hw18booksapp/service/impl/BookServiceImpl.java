package ru.otus.hw18booksapp.service.impl;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public BookDto getById(long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        return DtoConverter.getBookDto(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(DtoConverter::getBookDto)
                .toList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        var author = authorRepository.findByName(bookDto.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(bookDto.getGenre()).orElse(null);
        Book book = DtoConverter.getBook(bookDto.getTitle(), author, genre);
        var updateBook = bookRepository.save(book);
        return DtoConverter.getBookDto(updateBook);
    }

    @Transactional
    @Override
    public BookDto update(UpdateRequest request) {
        var author = authorRepository.findByName(request.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(request.getGenre()).orElse(null);
        Book book = new Book(request.getId(), author, genre, request.getTitle());
        var saveBook = bookRepository.save(book);
        return DtoConverter.getBookDto(saveBook);
    }
}