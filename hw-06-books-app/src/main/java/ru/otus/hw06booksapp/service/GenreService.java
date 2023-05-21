package ru.otus.hw06booksapp.service;

import ru.otus.hw06booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    long create(String fullName);

    List<Genre> getGenres();

    Genre getGenreById(long id);

    void update(long id, String title);

    void delete(long id);

}