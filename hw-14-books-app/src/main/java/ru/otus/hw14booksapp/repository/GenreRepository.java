package ru.otus.hw14booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14booksapp.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
