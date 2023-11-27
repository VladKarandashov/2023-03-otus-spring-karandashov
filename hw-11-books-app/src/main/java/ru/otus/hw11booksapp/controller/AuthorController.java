package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.repository.BookRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final BookRepository bookRepository;

    @GetMapping
    public Flux<Author> authorList() {
        return bookRepository.findAll().map(Book::getAuthor);
    }
}
