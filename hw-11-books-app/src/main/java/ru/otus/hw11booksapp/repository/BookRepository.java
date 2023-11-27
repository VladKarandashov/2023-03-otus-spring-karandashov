package ru.otus.hw11booksapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Book;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, String> {

    Mono<Book> findById(String id);

    Flux<Book> findAll();

    Mono<Long> count();
}