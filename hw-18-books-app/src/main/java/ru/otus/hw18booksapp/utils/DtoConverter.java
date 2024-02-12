package ru.otus.hw18booksapp.utils;

import lombok.experimental.UtilityClass;
import ru.otus.hw18booksapp.dto.BookDto;
import ru.otus.hw18booksapp.entity.Author;
import ru.otus.hw18booksapp.entity.Book;
import ru.otus.hw18booksapp.entity.Genre;

@UtilityClass
public class DtoConverter {

    public BookDto getBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName());
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, author, genre, title);
    }
}
