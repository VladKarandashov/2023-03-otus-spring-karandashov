package ru.otus.hw10booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw10booksapp.entity.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllBy();

    Optional<Author> findById(long id);

    Optional<Author> findByName(String name);

    Author save(Author author);

    void delete(Author author);
}