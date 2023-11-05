package ru.otus.hw11booksapp.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    Mono<Long> create(String name);

    Mono<Author> getById(long id);

    Mono<Author> getByName(String name);

    Flux<Author> getAll();

}