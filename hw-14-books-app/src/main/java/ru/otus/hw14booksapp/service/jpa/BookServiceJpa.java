package ru.otus.hw14booksapp.service.jpa;

import ru.otus.hw14booksapp.entity.Book;

import java.util.List;

public interface BookServiceJpa {

    Book getById(long id);

    List<Book> getAll();
}