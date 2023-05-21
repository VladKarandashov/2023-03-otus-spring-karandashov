package ru.otus.hw07booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07booksapp.entity.Book;
import ru.otus.hw07booksapp.entity.Note;
import ru.otus.hw07booksapp.exception.NotFoundException;
import ru.otus.hw07booksapp.repository.BookRepository;
import ru.otus.hw07booksapp.repository.NoteRepository;
import ru.otus.hw07booksapp.service.NoteService;

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
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        Note note = new Note(null, book, noteStr);
        return noteRepository.save(note).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    @Override
    public Note getNoteById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_EXIST));
    }

    @Transactional
    @Override
    public void update(long id, String newNote) {
        Note note = getNoteById(id);
        if (note == null) {
            throw new NotFoundException(NOTE_NOT_EXIST);
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

    @Transactional(readOnly = true)
    @Override
    public List<Note> getNoteByBookId(Long bookId) {
        return noteRepository.findAllByBook_Id(bookId);
    }
}