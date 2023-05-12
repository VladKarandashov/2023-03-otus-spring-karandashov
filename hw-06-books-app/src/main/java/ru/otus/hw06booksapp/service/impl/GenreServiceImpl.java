package ru.otus.hw06booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.exception.DaoException;
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
        Genre genre = new Genre(0, name);
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
        Genre genreById = genreRepository.getGenreById(id).orElse(null);
        if (genreById != null) {
            return genreById;
        }
        throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
    }

    @Transactional
    @Override
    public void update(long id, String name) {
        Genre genre = genreRepository.getGenreById(id).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }
        genre.setName(name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void delete(long genreId) {
        Genre genre = genreRepository.getGenreById(genreId).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }
        genreRepository.delete(genre);
    }
}