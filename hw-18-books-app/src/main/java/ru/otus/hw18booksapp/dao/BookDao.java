package ru.otus.hw18booksapp.dao;

import ru.otus.hw18booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    Book save(Book book);

}
