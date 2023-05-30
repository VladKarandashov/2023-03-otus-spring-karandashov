package ru.otus.hw07booksapp.service;

import ru.otus.hw07booksapp.dto.BookDto;
import ru.otus.hw07booksapp.entity.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    List<Book> getAll();

    Long getCount();

    void deleteById(long id);

    Book update(Book book);

    Book update(BookDto bookDto);
}