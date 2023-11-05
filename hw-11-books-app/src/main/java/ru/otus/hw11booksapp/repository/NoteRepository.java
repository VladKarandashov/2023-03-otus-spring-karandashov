package ru.otus.hw11booksapp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Note;

@Repository
public interface NoteRepository extends ReactiveCrudRepository<Note, Long> {

    Mono<Note> findById(long id);

    Flux<Note> findAll();

    Mono<Long> count();

    Mono<Void> deleteAllByBookId(long bookId);
}