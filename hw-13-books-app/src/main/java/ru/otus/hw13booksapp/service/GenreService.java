package ru.otus.hw13booksapp.service;

import ru.otus.hw13booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre create(String title);

    List<Genre> getAll();

    Genre getById(long id);

    void deleteById(long id);

}