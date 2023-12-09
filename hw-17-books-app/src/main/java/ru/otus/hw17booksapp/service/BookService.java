package ru.otus.hw17booksapp.service;

import ru.otus.hw17booksapp.dto.BookDto;
import ru.otus.hw17booksapp.dto.request.UpdateRequest;

import java.util.List;

public interface BookService {

    BookDto getById(long id);

    List<BookDto> getAll();

    void deleteById(long id);

    BookDto update(UpdateRequest book);

    BookDto create(BookDto bookDto);
}