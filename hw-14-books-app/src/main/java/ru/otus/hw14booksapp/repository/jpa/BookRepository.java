package ru.otus.hw14booksapp.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.jpa.BookJpa;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookJpa, Long> {

    @EntityGraph(value = "book-author-genre")
    Optional<BookJpa> findById(long id);

    @EntityGraph(value = "book-author-genre")
    List<BookJpa> findAllBy();
}