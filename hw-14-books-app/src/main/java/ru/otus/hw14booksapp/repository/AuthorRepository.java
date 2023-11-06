package ru.otus.hw14booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14booksapp.entity.AuthorJpa;

public interface AuthorRepository extends JpaRepository<AuthorJpa, Integer> {

}
