package ru.otus.hw14booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw14booksapp.entity.jpa.Book;
import ru.otus.hw14booksapp.view.EntityFormatter;

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
