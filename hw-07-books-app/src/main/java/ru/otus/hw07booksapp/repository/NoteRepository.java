package ru.otus.hw07booksapp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw07booksapp.entity.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository  extends JpaRepository<Note, Long> {

    @EntityGraph(value = "note-book")
    Optional<Note> findById(long id);

    Note save(Note note);

    @EntityGraph(value = "note-book")
    List<Note> findAllBy();

    @EntityGraph(value = "note-book")
    List<Note> findAllByBook_Id(long bookId);

    long countBy();

    void delete(Note note);

}