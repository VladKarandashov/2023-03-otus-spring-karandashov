package ru.otus.hw14booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14booksapp.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
