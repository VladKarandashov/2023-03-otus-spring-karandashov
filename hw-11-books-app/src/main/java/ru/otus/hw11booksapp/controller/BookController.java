package ru.otus.hw11booksapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.dto.request.UpdateRequest;
import ru.otus.hw11booksapp.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Mono<List<BookDto>> bookList() {
        return bookService.getAll().collectList();
    }

    @GetMapping("/{id}")
    public Mono<BookDto> book(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<BookDto>> create(@Valid @RequestBody BookDto book) {
        return bookService.create(book)
                .map(createdBook -> new ResponseEntity<>(createdBook, HttpStatus.CREATED));
    }

    @PutMapping
    public void update(@Valid @RequestBody UpdateRequest book) {
        bookService.update(book).subscribe();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
