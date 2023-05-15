package ru.otus.hw06booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.entity.Note;
import ru.otus.hw06booksapp.exception.DaoException;
import ru.otus.hw06booksapp.repository.BookRepository;
import ru.otus.hw06booksapp.repository.NoteRepository;
import ru.otus.hw06booksapp.service.NoteService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

    private static final String BOOK_NOT_EXIST = "Wasn't able to find book with this ID.";

    private final BookRepository bookRepository;

    private final NoteRepository noteRepository;



    @Transactional
    @Override
    public long create(Long bookId, String noteStr) {
        Book book = bookRepository.getBookById(bookId).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        Note note = new Note(null, book, noteStr);
        return noteRepository.save(note).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNote() {
        return noteRepository.getAllNote();
    }

    @Transactional(readOnly = true)
    @Override
    public Note getNoteById(long id) {
        Note note = noteRepository.getNoteById(id).orElse(null);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST, new RuntimeException());

        }
        return note;
    }

    @Transactional
    @Override
    public void update(long id, String newNote) {
        Note note = getNoteById(id);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST, new RuntimeException());
        }
        note.setNote(newNote);
        noteRepository.save(note);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }

}