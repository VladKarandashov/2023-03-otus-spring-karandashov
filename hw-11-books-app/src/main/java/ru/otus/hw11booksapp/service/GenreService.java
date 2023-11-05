package ru.otus.hw11booksapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    Mono<Long> create(String title);

    Flux<Genre> getAll();

    Mono<Genre> getById(long id);

    Mono<Genre> getByName(String name);

}