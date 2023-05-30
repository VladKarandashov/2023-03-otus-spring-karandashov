package ru.otus.hw07booksapp.service;

import ru.otus.hw07booksapp.entity.Genre;

import java.util.List;

public interface GenreService {

    long update(String title);

    List<Genre> getAll();

    Genre getById(long id);

    void update(long id, String title);

    void deleteById(long id);

}