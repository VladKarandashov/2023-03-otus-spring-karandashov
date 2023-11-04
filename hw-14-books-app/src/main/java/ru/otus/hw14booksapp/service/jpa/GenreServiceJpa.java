package ru.otus.hw14booksapp.service.jpa;

import ru.otus.hw14booksapp.entity.Genre;

import java.util.List;

public interface GenreServiceJpa {


    List<Genre> getAll();

    Genre getById(long id);

}