package ru.otus.hw11booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.exception.DaoException;
import ru.otus.hw11booksapp.exception.NotFoundException;
import ru.otus.hw11booksapp.repository.GenreRepository;
import ru.otus.hw11booksapp.service.GenreService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private static final String GENRE_NOT_EXIST = "Didn't find genre";

    private final GenreRepository genreRepository;

    
    @Override
    public Mono<Long> create(String name) {
        Genre genre = new Genre(null, name);
        return genreRepository.save(genre).map(Genre::getId);
    }

    @Override
    public Flux<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Mono<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Mono<Genre> getByName(String name) {
        return genreRepository.findByName(name);
    }
}