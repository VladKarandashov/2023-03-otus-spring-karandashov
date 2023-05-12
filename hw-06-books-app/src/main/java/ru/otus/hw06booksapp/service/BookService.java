package ru.otus.hw06booksapp.service;

import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.entity.Note;

import java.util.List;

public interface BookService {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Long getBooksCount();

    void deleteBook(long id);

    Book saveBook(Book book);

    Book saveBook(Long id, String newTitle);

    List<Note> getNoteByBookId(Long bookId);

}