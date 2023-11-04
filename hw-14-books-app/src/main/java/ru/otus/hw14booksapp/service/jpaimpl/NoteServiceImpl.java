package ru.otus.hw14booksapp.service.jpaimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.Note;
import ru.otus.hw14booksapp.entity.jpa.NoteJpa;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.jpa.NoteRepository;
import ru.otus.hw14booksapp.service.NoteService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public NoteJpa getNoteById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAllBy().stream()
                .map(noteJpa -> (Note) noteJpa)
                .collect(Collectors.toList());
    }
}