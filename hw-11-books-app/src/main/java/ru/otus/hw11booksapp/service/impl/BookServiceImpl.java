package ru.otus.hw11booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.BookCompleteDto;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.dto.request.UpdateRequest;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.exception.DaoException;
import ru.otus.hw11booksapp.exception.NotFoundException;
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.repository.GenreRepository;
import ru.otus.hw11booksapp.repository.NoteRepository;
import ru.otus.hw11booksapp.service.BookService;
import ru.otus.hw11booksapp.utils.DtoConverter;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final NoteRepository noteRepository;

    private final DtoConverter dtoConverter;

    @Override
    public Mono<BookDto> getById(long id) {
        return bookRepository.findById(id)
                .flatMap(dtoConverter::getBookDto);
    }

    @Override
    public Mono<BookCompleteDto> getCompleteById(long id) {
        return bookRepository.findById(id)
                .flatMap(dtoConverter::getCompleteBookDto);
    }

    @Override
    public Flux<BookDto> getAll() {
        return bookRepository.findAll()
                .flatMap(dtoConverter::getBookDto);
    }

    
    @Override
    public void deleteById(long id) {
        // удаляем комментарии к книге
        noteRepository.deleteAllByBookId(id).subscribe();
        // удаляем саму книгу
        bookRepository.deleteById(id).subscribe();
    }

    
    @Override
    public Mono<BookDto> create(BookDto bookDto) {
        var author = authorRepository.findByName(bookDto.getAuthor());
        var genre = genreRepository.findByName(bookDto.getGenre());
        return Mono.zip(author, genre)
                .map(t -> dtoConverter.getBook(bookDto.getTitle(), t.getT1(), t.getT2()))
                .flatMap(bookRepository::save)
                .flatMap(dtoConverter::getBookDto);
    }

    
    @Override
    public Mono<BookDto> update(UpdateRequest request) {
        var authorId = authorRepository.findByName(request.getAuthor()).map(Author::getId);
        var genreId = genreRepository.findByName(request.getGenre()).map(Genre::getId);
        return Mono.zip(authorId, genreId)
                .map(t -> new Book(request.getId(), t.getT1(), t.getT2(), request.getTitle()))
                .flatMap(bookRepository::save)
                .flatMap(dtoConverter::getBookDto);
    }
}