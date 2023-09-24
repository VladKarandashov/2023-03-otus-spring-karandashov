package ru.otus.hw08booksapp.service;

import ru.otus.hw08booksapp.entity.Note;

import java.util.List;

public interface NoteService {

    String create(String bookId, String note);

    List<Note> getAllNote();

    Note getNoteById(String id);

    void update(String id, String newNote);

    void deleteById(String id);

    void deleteByBookId(String id);
}