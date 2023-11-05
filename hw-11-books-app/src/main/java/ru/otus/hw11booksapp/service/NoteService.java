package ru.otus.hw11booksapp.service;

import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.entity.Note;

import java.util.List;

public interface NoteService {

    Mono<Long> create(Long bookId, String note);

    Mono<Note> getNoteById(long id);
}