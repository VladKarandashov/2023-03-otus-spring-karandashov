package ru.otus.hw05booksapp.service;

import ru.otus.hw05booksapp.entity.Book;

import java.util.List;

public interface BookService {

    int getCount();

    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

    Book updateTitleById(long id, String newTitle);

    Book insert(long authorId, long genreId, String bookTitle);
}
