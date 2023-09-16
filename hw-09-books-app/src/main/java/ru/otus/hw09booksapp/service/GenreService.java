package ru.otus.hw09booksapp.service;

import ru.otus.hw09booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    long create(String title);

    List<Genre> getAll();

    Genre getById(long id);

    Genre getByName(String name);

    void update(long id, String title);

    void deleteById(long id);

}