package ru.otus.hw11booksapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Genre;

@Repository
public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

    Mono<Genre> findById(long id);

    Mono<Genre> findByName(String name);

    Flux<Genre> findAll();
}