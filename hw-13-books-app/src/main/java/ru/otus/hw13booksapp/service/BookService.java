package ru.otus.hw13booksapp.service;

import ru.otus.hw13booksapp.dto.BookDto;
import ru.otus.hw13booksapp.dto.request.UpdateRequest;

import java.util.List;

public interface BookService {

    BookDto getById(long id);

    List<BookDto> getAll();

    Long getCount();

    void deleteById(long id);

    BookDto update(UpdateRequest book);

    BookDto create(BookDto bookDto);
}