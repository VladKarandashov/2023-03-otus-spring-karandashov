package ru.otus.hw11booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.exception.DaoException;
import ru.otus.hw11booksapp.exception.NotFoundException;
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    
    @Override
    public Mono<Long> create(String fullName) {
        return authorRepository.save(new Author(null, fullName))
                .map(Author::getId);
    }

    @Override
    public Mono<Author> getById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Mono<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Flux<Author> getAll() {
        return authorRepository.findAll();
    }
}