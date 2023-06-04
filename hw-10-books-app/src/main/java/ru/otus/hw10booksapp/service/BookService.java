package ru.otus.hw10booksapp.service;

import ru.otus.hw10booksapp.dto.BookDto;
import ru.otus.hw10booksapp.entity.Book;

import java.util.List;

public interface BookService {

    Book getById(long id);

    List<Book> getAll();

    List<BookDto> getAllDto();

    Long getCount();

    void deleteById(long id);

    Book update(Book book);

    Book create(BookDto bookDto);
}