package ru.otus.hw08booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08booksapp.entity.Book;
import ru.otus.hw08booksapp.entity.Note;
import ru.otus.hw08booksapp.exception.NotFoundException;
import ru.otus.hw08booksapp.repository.NoteRepository;
import ru.otus.hw08booksapp.service.BookService;
import ru.otus.hw08booksapp.service.NoteService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

    private final BookService bookService;

    private final NoteRepository noteRepository;

    @Transactional
    @Override
    public String create(String bookId, String noteStr) {
        Book book = bookService.getById(bookId);
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
    public Note getNoteById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_EXIST));
    }

    @Transactional
    @Override
    public void update(String id, String newNote) {
        Note note = getNoteById(id);
        note.setNote(newNote);
        noteRepository.save(note);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void deleteByBookId(String bookId) {
        noteRepository.deleteAllByBook_Id(bookId);
    }
}