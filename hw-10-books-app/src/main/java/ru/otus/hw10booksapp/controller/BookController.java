package ru.otus.hw10booksapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10booksapp.dto.BookDto;
import ru.otus.hw10booksapp.entity.Book;
import ru.otus.hw10booksapp.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> bookList() {
        return bookService.getAllDto();
    }

    @GetMapping("/{id}")
    public Book book(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book create(@RequestBody BookDto book) {
        return bookService.create(book);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        var books = bookService.update(book);
        log.info("______________________" + books);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
