package ru.otus.hw06booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.exception.NotFoundException;
import ru.otus.hw06booksapp.repository.GenreRepository;
import ru.otus.hw06booksapp.service.GenreService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private static final String GENRE_NOT_EXIST = "Didn't find genre";

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public long create(String name) {
        Genre genre = new Genre(null, name);
        return genreRepository.save(genre).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getGenres() {
        return genreRepository.getAllGenres();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(long id) {
        return genreRepository.getGenreById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
    }

    @Transactional
    @Override
    public void update(long id, String name) {
        Genre genre = genreRepository.getGenreById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
        genre.setName(name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void delete(long genreId) {
        genreRepository.getGenreById(genreId).ifPresent(genreRepository::delete);
    }
}