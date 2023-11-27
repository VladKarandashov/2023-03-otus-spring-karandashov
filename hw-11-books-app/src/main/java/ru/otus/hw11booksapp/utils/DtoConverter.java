package ru.otus.hw11booksapp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.*;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;

@Service
@RequiredArgsConstructor
public class DtoConverter {

    public Mono<BookDto> getBookDto(Book book) {
        var authorName = Mono.just(book.getAuthor().getName());
        var genreName = Mono.just(book.getGenre().getName());
        return Mono.zip(authorName, genreName).map(t -> new BookDto(book.getId(), book.getTitle(), t.getT1(), t.getT2()));
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, author, genre, title);
    }
}
