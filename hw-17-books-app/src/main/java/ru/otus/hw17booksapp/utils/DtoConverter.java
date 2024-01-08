package ru.otus.hw17booksapp.utils;

import lombok.experimental.UtilityClass;
import ru.otus.hw17booksapp.dto.*;
import ru.otus.hw17booksapp.entity.Author;
import ru.otus.hw17booksapp.entity.Book;
import ru.otus.hw17booksapp.entity.Genre;

@UtilityClass
public class DtoConverter {

    public BookDto getBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName());
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, author, genre, title);
    }
}
