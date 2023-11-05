package ru.otus.hw11booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Note;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.repository.NoteRepository;
import ru.otus.hw11booksapp.service.NoteService;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;

    private final NoteRepository noteRepository;

    @Override
    public Mono<Long> create(Long bookId, String noteStr) {
        return bookRepository.findById(bookId)
                .map(book -> new Note(null, book.getId(), noteStr))
                .flatMap(noteRepository::save)
                .map(Note::getId);
    }

    @Override
    public Mono<Note> getNoteById(long id) {
        return noteRepository.findById(id);
    }
}