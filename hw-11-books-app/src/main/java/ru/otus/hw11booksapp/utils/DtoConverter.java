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
        return Mono.just(new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName()));
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, title, author, genre);
    }
}
