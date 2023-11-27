package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.BookRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final BookRepository bookRepository;

    @GetMapping
    public Flux<Genre> genreList() {
        return bookRepository.findAll().map(Book::getGenre);
    }
}
