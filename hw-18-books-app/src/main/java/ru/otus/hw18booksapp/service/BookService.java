package ru.otus.hw18booksapp.service;

import ru.otus.hw18booksapp.dto.BookDto;
import ru.otus.hw18booksapp.dto.request.UpdateRequest;

import java.util.List;

public interface BookService {

    BookDto getById(long id);

    List<BookDto> getAll();

    void deleteById(long id);

    BookDto update(UpdateRequest book);

    BookDto create(BookDto bookDto);
}