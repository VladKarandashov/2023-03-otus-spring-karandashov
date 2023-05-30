package ru.otus.hw07booksapp.service;


import ru.otus.hw07booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    long update(String name);

    Author getById(long id);

    List<Author> getAll();

    void update(long id, String name);

    void deleteById(long id);

}