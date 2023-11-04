package ru.otus.hw14booksapp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.jpa.AuthorJpa;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorJpa, Long> {

    List<AuthorJpa> findAllBy();

    Optional<AuthorJpa> findById(long id);
}