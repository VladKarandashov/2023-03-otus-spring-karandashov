package ru.otus.hw11booksapp.service;

import reactor.core.publisher.Mono;

public interface NoteService {

    Mono<Long> create(Long bookId, String note);
}