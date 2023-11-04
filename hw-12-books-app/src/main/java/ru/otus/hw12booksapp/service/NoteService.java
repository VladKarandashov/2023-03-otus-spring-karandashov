package ru.otus.hw12booksapp.service;

import ru.otus.hw12booksapp.entity.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String note);

    List<Note> getAllNote();

    Note getNoteById(long id);

    void update(long id, String newNote);

    void deleteById(long id);

    void deleteByBookId(long id);
}