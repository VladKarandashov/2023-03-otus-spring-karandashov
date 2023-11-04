package ru.otus.hw14booksapp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.jpa.GenreJpa;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreJpa, Long> {

    Optional<GenreJpa> findById(long id);

    List<GenreJpa> findAllBy();
}