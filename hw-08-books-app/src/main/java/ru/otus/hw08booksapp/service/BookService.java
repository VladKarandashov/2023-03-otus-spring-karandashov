package ru.otus.hw08booksapp.service;

import ru.otus.hw08booksapp.dto.BookDto;
import ru.otus.hw08booksapp.entity.Book;

import java.util.List;

public interface BookService {

    Book getById(String id);

    List<Book> getAll();

    Long getCount();

    void deleteById(String id);

    Book update(Book book);

    String create(BookDto bookDto);
}