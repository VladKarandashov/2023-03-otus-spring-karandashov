package ru.otus.hw07booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw07booksapp.entity.Genre;
import ru.otus.hw07booksapp.view.EntityFormatter;

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
