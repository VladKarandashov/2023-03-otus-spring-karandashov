package ru.otus.hw09booksapp.service;


import ru.otus.hw09booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);

    Author getById(long id);

    Author getByName(String name);

    List<Author> getAll();

    void update(long id, String name);

    void deleteById(long id);

}