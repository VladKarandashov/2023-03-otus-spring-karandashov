package ru.otus.hw07booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw07booksapp.entity.Book;
import ru.otus.hw07booksapp.view.EntityFormatter;

@Service
public class BookFormatter implements EntityFormatter<Book> {

    @Override
    public String format(Book entity) {
        return "Book{" +
                "id=" + entity.getId() +
                ", title='" + entity.getTitle() + '\'' +
                '}';
    }
}
