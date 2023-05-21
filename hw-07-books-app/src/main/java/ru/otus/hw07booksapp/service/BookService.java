package ru.otus.hw07booksapp.service;

import ru.otus.hw07booksapp.dto.BookDto;
import ru.otus.hw07booksapp.entity.Book;

import java.util.List;

public interface BookService {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Long getBooksCount();

    void deleteBook(long id);

    Book createBook(Book book);

    Book createBook(Long id, String newTitle);

    Book createBook(BookDto bookDto);
}