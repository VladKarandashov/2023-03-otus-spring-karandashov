package ru.otus.hw14booksapp.service.mongo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw14booksapp.entity.Genre;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.mongo.GenreMongoRepository;
import ru.otus.hw14booksapp.service.mongo.GenreServiceMongo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreServiceMongoImpl implements GenreServiceMongo {

    private static final String AUTHOR_NOT_EXIST = "no genre found by id";

    private final GenreMongoRepository genreMongoRepository;

    @Override
    public Genre getById(String id) {
        return genreMongoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Override
    public List<Genre> getAll() {
        return genreMongoRepository.findAllBy().stream()
                .map(genreMongo -> (Genre) genreMongo)
                .collect(Collectors.toList());
    }
}