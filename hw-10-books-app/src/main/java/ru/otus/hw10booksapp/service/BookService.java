package ru.otus.hw10booksapp.service;

import ru.otus.hw10booksapp.dto.BookCompleteDto;
import ru.otus.hw10booksapp.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto getById(long id);

    BookCompleteDto getCompleteById(long id);

    List<BookDto> getAll();

    Long getCount();

    void deleteById(long id);

    BookDto update(BookDto book);

    BookDto update(BookCompleteDto bookDto);

    BookDto create(BookDto bookDto);
}