package ru.otus.hw13booksapp.service;


import ru.otus.hw13booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    Author create(String name);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

}