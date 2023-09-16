package ru.otus.hw09booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09booksapp.dto.BookCompleteDto;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.entity.Book;
import ru.otus.hw09booksapp.exception.NotFoundException;
import ru.otus.hw09booksapp.repository.AuthorRepository;
import ru.otus.hw09booksapp.repository.BookRepository;
import ru.otus.hw09booksapp.repository.GenreRepository;
import ru.otus.hw09booksapp.repository.NoteRepository;
import ru.otus.hw09booksapp.service.BookService;
import ru.otus.hw09booksapp.utils.DtoConverter;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

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
        return DtoConverter.getCompleteBookDto(book);
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
        var author = authorRepository.findByName(bookDto.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(bookDto.getGenre()).orElse(null);
        Book book = DtoConverter.getBook(bookDto.getTitle(), author, genre);
        var updateBook = bookRepository.save(book);
        return DtoConverter.getBookDto(updateBook);
    }

    @Transactional
    @Override
    public BookDto update(BookDto bookDto) {
        var author = authorRepository.findByName(bookDto.getAuthor()).orElse(null);
        var genre = genreRepository.findByName(bookDto.getGenre()).orElse(null);
        Book book = new Book(bookDto.getId(), author, genre, bookDto.getTitle());
        var saveBook = bookRepository.save(book);
        return DtoConverter.getBookDto(saveBook);
    }

    @Transactional
    @Override
    public BookDto update(BookCompleteDto bookDto) {
        var author = authorRepository.findById(bookDto.getAuthor().getId()).orElse(null);
        var genre = genreRepository.findById(bookDto.getGenre().getId()).orElse(null);
        Book book = new Book(bookDto.getId(), author, genre, bookDto.getTitle());
        var saveBook = bookRepository.save(book);
        return DtoConverter.getBookDto(saveBook);
    }
}