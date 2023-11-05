package ru.otus.hw11booksapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Author;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

    Flux<Author> findAll();

    Mono<Author> findById(long id);

    Mono<Author> findByName(String name);
}