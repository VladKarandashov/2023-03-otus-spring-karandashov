package ru.otus.hw14booksapp.service.mongo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.Note;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.mongo.NoteMongoRepository;
import ru.otus.hw14booksapp.service.mongo.NoteServiceMongo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteServiceMongoImpl implements NoteServiceMongo {

    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";

    private final NoteMongoRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAllBy().stream()
                .map(noteMongo -> (Note) noteMongo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Note getNoteById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_EXIST));
    }
}