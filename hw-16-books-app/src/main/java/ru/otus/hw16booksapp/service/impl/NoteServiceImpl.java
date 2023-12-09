package ru.otus.hw16booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw16booksapp.entity.Book;
import ru.otus.hw16booksapp.entity.Note;
import ru.otus.hw16booksapp.exception.NotFoundException;
import ru.otus.hw16booksapp.repository.BookRepository;
import ru.otus.hw16booksapp.repository.NoteRepository;
import ru.otus.hw16booksapp.service.NoteService;

import java.util.List;

import static ru.otus.hw16booksapp.service.impl.BookServiceImpl.BOOK_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

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
        return noteRepository.findAll();
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
        note.setNote(newNote);
        noteRepository.save(note);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void deleteByBookId(long bookId) {
        noteRepository.deleteAllByBook_Id(bookId);
    }
}