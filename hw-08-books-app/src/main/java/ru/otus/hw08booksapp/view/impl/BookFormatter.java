package ru.otus.hw08booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw08booksapp.entity.Book;
import ru.otus.hw08booksapp.view.EntityFormatter;

@Service
public class BookFormatter implements EntityFormatter<Book> {

    @Override
    public String format(Book entity) {
        return "Book{" +
                "id=" + entity.getId() +
                ", title='" + entity.getTitle() + '\'' +
                ", author='" + entity.getAuthor() + '\'' +
                ", genre='" + entity.getGenre() + '\'' +
                '}';
    }
}
