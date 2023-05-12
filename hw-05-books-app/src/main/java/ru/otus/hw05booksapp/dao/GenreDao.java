package ru.otus.hw05booksapp.dao;

import ru.otus.hw05booksapp.entity.Genre;

public interface GenreDao {

    Genre findById(long id);

    Genre findByTitle(String title);

}