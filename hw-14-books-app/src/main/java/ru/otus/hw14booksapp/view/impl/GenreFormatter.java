package ru.otus.hw14booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw14booksapp.entity.Genre;
import ru.otus.hw14booksapp.view.EntityFormatter;

@Service
public class GenreFormatter implements EntityFormatter<Genre> {

    @Override
    public String format(Genre entity) {
        return "Genre{" +
                "id=" + entity.getId() +
                ", name='" + entity.getName() + '\'' +
                '}';
    }
}
