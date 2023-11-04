package ru.otus.hw14booksapp.service;


import ru.otus.hw14booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    Author getById(long id);

    List<Author> getAll();
}