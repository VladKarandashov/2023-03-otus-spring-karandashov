package ru.otus.hw09booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw09booksapp.entity.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(long id);

    Note save(Note note);

    List<Note> findAllBy();

    List<Note> findAllByBook_Id(long bookId);

    long countBy();

    void delete(Note note);

    void deleteAllByBook_Id(long bookId);
}