package ru.otus.hw14booksapp.service.jpa;

import ru.otus.hw14booksapp.entity.Note;

import java.util.List;

public interface NoteServiceJpa {
    List<Note> getAllNote();

    Note getNoteById(long id);
}