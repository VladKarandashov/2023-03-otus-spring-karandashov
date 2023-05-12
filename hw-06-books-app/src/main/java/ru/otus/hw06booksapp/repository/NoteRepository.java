package ru.otus.hw06booksapp.repository;

import ru.otus.hw06booksapp.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    Optional<Note> getNoteById(long id);

    Note save(Note note);

    List<Note> getAllNote();

    List<Note> getNoteByBookId(long noteId);

    long countNote();

    void delete(Note note);

}