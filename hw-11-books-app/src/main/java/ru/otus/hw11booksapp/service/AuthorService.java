package ru.otus.hw11booksapp.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Author;

public interface AuthorService {

    Mono<Long> create(String name);

    Flux<Author> getAll();

}