package ru.otus.hw13booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw13booksapp.entity.Book;
import ru.otus.hw13booksapp.entity.Note;
import ru.otus.hw13booksapp.exception.NotFoundException;
import ru.otus.hw13booksapp.repository.BookRepository;
import ru.otus.hw13booksapp.repository.NoteRepository;
import ru.otus.hw13booksapp.service.NoteService;

import static ru.otus.hw13booksapp.service.impl.BookServiceImpl.BOOK_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;

    private final NoteRepository noteRepository;

    @Transactional
    @Override
    public Note create(Long bookId, String noteStr) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
        Note note = new Note(null, book, noteStr);
        return noteRepository.save(note);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }
}