package ru.otus.hw14booksapp.service;

import ru.otus.hw14booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    long create(String title);

    List<Genre> getAll();

    Genre getById(long id);

    void update(long id, String title);

    void deleteById(long id);

}