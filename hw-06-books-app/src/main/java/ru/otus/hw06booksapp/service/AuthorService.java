package ru.otus.hw06booksapp.service;

import ru.otus.hw06booksapp.entity.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);

    Author getById(long id);

    List<Author> getAll();

    void update(long id, String fullName);

    void delete(long id);

}