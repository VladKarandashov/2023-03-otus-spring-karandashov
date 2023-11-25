package ru.otus.hw16booksapp.service;

import ru.otus.hw16booksapp.dto.BookCompleteDto;
import ru.otus.hw16booksapp.dto.BookDto;
import ru.otus.hw16booksapp.dto.request.UpdateRequest;

import java.util.List;

public interface BookService {

    BookDto getById(long id);

    BookCompleteDto getCompleteById(long id);

    List<BookDto> getAll();

    Long getCount();

    void deleteById(long id);

    BookDto update(UpdateRequest book);

    BookDto update(BookCompleteDto bookDto);

    BookDto create(BookDto bookDto);
}