package ru.otus.hw06booksapp.repository;

import ru.otus.hw06booksapp.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(long id);

    Author save(Author author);

    void delete(Author author);
}