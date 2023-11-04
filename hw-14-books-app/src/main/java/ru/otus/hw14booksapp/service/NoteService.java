package ru.otus.hw14booksapp.service;

import ru.otus.hw14booksapp.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNote();

    Note getNoteById(long id);
}