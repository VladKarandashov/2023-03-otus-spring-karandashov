package ru.otus.hw14booksapp.service.jpaimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.jpa.Note;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.jpa.NoteRepository;
import ru.otus.hw14booksapp.service.NoteService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

    private final NoteRepository noteRepository;

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
}