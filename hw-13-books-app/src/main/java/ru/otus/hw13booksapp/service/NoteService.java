package ru.otus.hw13booksapp.service;

import ru.otus.hw13booksapp.entity.Note;

public interface NoteService {

    Note create(Long bookId, String note);

    Note create(Note note);

    void deleteById(long id);
}