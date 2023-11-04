package ru.otus.hw14booksapp.service.mongo;

import ru.otus.hw14booksapp.entity.Book;

import java.util.List;

public interface BookServiceMongo {

    Book getById(String id);

    List<Book> getAll();
}