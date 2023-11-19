package ru.otus.hw11booksapp.utils;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.*;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class DtoConverter {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public Mono<BookDto> getBookDto(Book book) {
        var authorName = authorRepository.findById(book.getAuthorId()).map(Author::getName);
        var genreName = genreRepository.findById(book.getGenreId()).map(Genre::getName);
        return Mono.zip(authorName, genreName).map(t -> new BookDto(book.getId(), book.getTitle(), t.getT1(), t.getT2()));
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, author.getId(), genre.getId(), title);
    }

    public AuthorDto getAuthorDto(@Nullable Author author) {
        if (author == null) return null;
        return new AuthorDto(author.getId(), author.getName());
    }

    public GenreDto getGenreDto(@Nullable Genre genre) {
        if (genre == null) return null;
        return new GenreDto(genre.getId(), genre.getName());
    }
}
