package ru.otus.hw11booksapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.BookCompleteDto;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.dto.request.UpdateRequest;

import java.util.List;

public interface BookService {

    Mono<BookDto> getById(long id);

    Mono<BookCompleteDto> getCompleteById(long id);

    Flux<BookDto> getAll();

    void deleteById(long id);

    Mono<BookDto> update(UpdateRequest book);

    Mono<BookDto> create(BookDto bookDto);
}