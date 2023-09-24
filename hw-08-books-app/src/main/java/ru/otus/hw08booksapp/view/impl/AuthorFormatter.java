package ru.otus.hw08booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw08booksapp.entity.Author;
import ru.otus.hw08booksapp.view.EntityFormatter;

@Service
public class AuthorFormatter implements EntityFormatter<Author> {

    @Override
    public String format(Author author) {
        return "Author{" +
                "id=" + author.getId() +
                ", name='" + author.getName() + '\'' +
                '}';
    }
}
