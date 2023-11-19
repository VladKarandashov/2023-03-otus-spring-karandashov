package ru.otus.hw13booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw13booksapp.dto.BookDto;
import ru.otus.hw13booksapp.dto.request.UpdateRequest;
import ru.otus.hw13booksapp.entity.Book;
import ru.otus.hw13booksapp.exception.NotFoundException;
import ru.otus.hw13booksapp.repository.AuthorRepository;
import ru.otus.hw13booksapp.repository.BookRepository;
import ru.otus.hw13booksapp.repository.GenreRepository;
import ru.otus.hw13booksapp.service.BookService;
import ru.otus.hw13booksapp.utils.DtoConverter;

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

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {
        return bookRepository.count();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        var author = authorRepository.findByName(bookDto.getAuthor())
                .orElseThrow(() -> new NotFoundException("Author not exist"));
        var genre = genreRepository.findByName(bookDto.getGenre())
                .orElseThrow(() -> new NotFoundException("Genre not exist"));
        Book book = DtoConverter.getBook(bookDto.getTitle(), author, genre);
        var updateBook = bookRepository.save(book);
        return DtoConverter.getBookDto(updateBook);
    }

    @Transactional
    @Override
    public BookDto update(UpdateRequest request) {
        var book = bookRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Book not exist"));
        var author = authorRepository.findByName(request.getAuthor())
                .orElseThrow(() -> new NotFoundException("Author not exist"));
        var genre = genreRepository.findByName(request.getGenre())
                .orElseThrow(() -> new NotFoundException("Genre not exist"));
        book.setAuthor(author);
        book.setGenre(genre);
        book.setTitle(request.getTitle());
        var saveBook = bookRepository.save(book);
        return DtoConverter.getBookDto(saveBook);
    }
}