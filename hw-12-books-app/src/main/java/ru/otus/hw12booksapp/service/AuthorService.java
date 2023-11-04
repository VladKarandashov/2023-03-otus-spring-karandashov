package ru.otus.hw12booksapp.service;


import ru.otus.hw12booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);

    Author getById(long id);

    Author getByName(String name);

    List<Author> getAll();

    void update(long id, String name);

    void deleteById(long id);

}