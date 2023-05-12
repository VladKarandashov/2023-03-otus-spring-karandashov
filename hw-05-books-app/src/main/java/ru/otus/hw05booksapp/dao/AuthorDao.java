package ru.otus.hw05booksapp.dao;

import ru.otus.hw05booksapp.entity.Author;

public interface AuthorDao {

    Author findById(long id);

    Author findByName(String name);

}