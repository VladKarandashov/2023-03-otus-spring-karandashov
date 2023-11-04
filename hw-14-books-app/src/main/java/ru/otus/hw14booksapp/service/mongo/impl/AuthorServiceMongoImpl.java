package ru.otus.hw14booksapp.service.mongo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw14booksapp.entity.Author;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.mongo.AuthorMongoRepository;
import ru.otus.hw14booksapp.service.mongo.AuthorServiceMongo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorServiceMongoImpl implements AuthorServiceMongo {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorMongoRepository authorRepository;

    @Override
    public Author getById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAllBy().stream()
                .map(authorMongo -> (Author) authorMongo)
                .collect(Collectors.toList());
    }
}