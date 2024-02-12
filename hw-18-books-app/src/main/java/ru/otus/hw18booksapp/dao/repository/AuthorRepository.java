package ru.otus.hw18booksapp.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.otus.hw18booksapp.entity.Author;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path = "author")
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();

    Optional<Author> findByName(String name);
}