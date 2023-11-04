package ru.otus.hw14booksapp.service.mongo;

import ru.otus.hw14booksapp.entity.Genre;

import java.util.List;

public interface GenreServiceMongo {


    List<Genre> getAll();

    Genre getById(String id);

}