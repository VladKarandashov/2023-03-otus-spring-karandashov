package ru.otus.hw14booksapp.service;

import ru.otus.hw14booksapp.dto.BookDto;
import ru.otus.hw14booksapp.entity.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    List<Book> getAll();

    Long getCount();

    void deleteById(long id);

    Book update(Book book);

    Book create(BookDto bookDto);
}