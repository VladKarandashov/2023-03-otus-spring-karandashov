package ru.otus.hw07booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw07booksapp.entity.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findById(long id);

    List<Genre> findAllBy();

    Genre save(Genre genre);

    void delete(Genre genre);

}