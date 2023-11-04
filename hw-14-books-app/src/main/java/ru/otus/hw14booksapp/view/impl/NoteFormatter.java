package ru.otus.hw14booksapp.view.impl;

import org.springframework.stereotype.Service;
import ru.otus.hw14booksapp.entity.jpa.Note;
import ru.otus.hw14booksapp.view.EntityFormatter;

@Service
public class NoteFormatter implements EntityFormatter<Note> {
    @Override
    public String format(Note entity) {
        return "Note{" +
                "id=" + entity.getId() +
                ", note='" + entity.getNote() + '\'' +
                '}';
    }
}
