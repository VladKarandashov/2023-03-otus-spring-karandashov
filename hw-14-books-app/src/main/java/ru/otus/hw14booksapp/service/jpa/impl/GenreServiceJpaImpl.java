package ru.otus.hw14booksapp.service.jpa.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.Genre;
import ru.otus.hw14booksapp.entity.jpa.GenreJpa;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.jpa.GenreRepository;
import ru.otus.hw14booksapp.service.jpa.GenreServiceJpa;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreServiceJpaImpl implements GenreServiceJpa {
    private static final String GENRE_NOT_EXIST = "Didn't find genre";

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public GenreJpa getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAllBy().stream()
                .map(genreJpa -> (Genre) genreJpa)
                .collect(Collectors.toList());
    }
}