package ru.otus.hw14booksapp.service.mongo;

import ru.otus.hw14booksapp.entity.Note;

import java.util.List;

public interface NoteServiceMongo {
    List<Note> getAllNote();

    Note getNoteById(String id);
}