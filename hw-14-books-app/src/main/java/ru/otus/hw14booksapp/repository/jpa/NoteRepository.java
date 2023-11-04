package ru.otus.hw14booksapp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.jpa.NoteJpa;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteJpa, Long> {

    Optional<NoteJpa> findById(long id);

    List<NoteJpa> findAllBy();
}