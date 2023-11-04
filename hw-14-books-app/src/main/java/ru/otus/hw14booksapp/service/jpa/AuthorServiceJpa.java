package ru.otus.hw14booksapp.service.jpa;


import ru.otus.hw14booksapp.entity.Author;

import java.util.List;

public interface AuthorServiceJpa {

    Author getById(long id);

    List<Author> getAll();
}