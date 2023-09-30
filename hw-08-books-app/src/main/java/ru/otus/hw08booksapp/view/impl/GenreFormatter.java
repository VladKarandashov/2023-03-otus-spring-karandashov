package ru.otus.hw08booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw08booksapp.entity.Genre;
import ru.otus.hw08booksapp.view.EntityFormatter;

@Service
public class GenreFormatter implements EntityFormatter<Genre> {

    @Override
    public String format(Genre entity) {
        return "Genre{" +
                "name='" + entity.getName() + '\'' +
                '}';
    }
}
