package ru.otus.hw14booksapp.service;


import ru.otus.hw14booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);

    Author getById(long id);

    List<Author> getAll();

    void update(long id, String name);

    void deleteById(long id);

}