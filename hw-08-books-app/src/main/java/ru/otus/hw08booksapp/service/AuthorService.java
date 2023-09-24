package ru.otus.hw08booksapp.service;


import ru.otus.hw08booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    String create(String name);

    Author getById(String id);

    List<Author> getAll();

    void update(String id, String name);

    void deleteById(String id);

}