package ru.otus.hw13booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw13booksapp.entity.Genre;
import ru.otus.hw13booksapp.exception.NotFoundException;
import ru.otus.hw13booksapp.repository.GenreRepository;
import ru.otus.hw13booksapp.service.GenreService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private static final String GENRE_NOT_EXIST = "Didn't find genre";

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public Genre create(String name) {
        Genre genre = new Genre(null, name);
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
    }

    @Transactional
    @Override
    public void deleteById(long genreId) {
        genreRepository.deleteById(genreId);
    }
}