package ru.otus.hw14booksapp.service;

import ru.otus.hw14booksapp.entity.jpa.Genre;

import java.util.List;

public interface GenreService {


    List<Genre> getAll();

    Genre getById(long id);

}