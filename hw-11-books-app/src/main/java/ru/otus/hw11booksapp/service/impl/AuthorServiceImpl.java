package ru.otus.hw11booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.service.AuthorService;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public Mono<Long> create(String fullName) {
        return authorRepository.save(new Author(null, fullName))
                .map(Author::getId);
    }

    @Override
    public Flux<Author> getAll() {
        return authorRepository.findAll();
    }
}