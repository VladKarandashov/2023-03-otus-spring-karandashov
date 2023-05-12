package ru.otus.hw05booksapp.dao;

import ru.otus.hw05booksapp.entity.Book;

import java.util.List;

public interface BookDao {

    int getCount();

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

    void updateTitleById(long id, String newTitle);

    void updateById(Book book);

    Book insert(Book book);

}