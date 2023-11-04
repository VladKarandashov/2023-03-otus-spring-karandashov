package ru.otus.hw14booksapp.service.mongo;


import ru.otus.hw14booksapp.entity.Author;

import java.util.List;

public interface AuthorServiceMongo {

    Author getById(String id);

    List<Author> getAll();
}