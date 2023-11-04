package ru.otus.hw14booksapp.service;

import ru.otus.hw14booksapp.entity.jpa.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    List<Book> getAll();
}