package ru.otus.hw06booksapp.service;

import ru.otus.hw06booksapp.entity.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String note);

    List<Note> getAllNote();

    Note getNoteById(long id);

    List<Note> getNoteByBookId(Long bookId);

    void update(long id, String newNote);

    void delete(long id);

}