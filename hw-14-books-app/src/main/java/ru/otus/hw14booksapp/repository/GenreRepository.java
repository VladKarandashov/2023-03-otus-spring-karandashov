package ru.otus.hw14booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14booksapp.entity.GenreJpa;

public interface GenreRepository extends JpaRepository<GenreJpa, Integer> {

}
