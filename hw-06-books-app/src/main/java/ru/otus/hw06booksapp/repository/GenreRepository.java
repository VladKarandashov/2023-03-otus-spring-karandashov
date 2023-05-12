package ru.otus.hw06booksapp.repository;

import ru.otus.hw06booksapp.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> getGenreById(long id);

    List<Genre> getAllGenres();

    Genre save(Genre genre);

    void delete(Genre genre);

}