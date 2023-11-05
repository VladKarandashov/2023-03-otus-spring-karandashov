package ru.otus.hw13booksapp.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw13booksapp.dto.BookDto;
import ru.otus.hw13booksapp.dto.request.UpdateRequest;
import ru.otus.hw13booksapp.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    public List<BookDto> bookList() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto book(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto book) {
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@Valid @RequestBody UpdateRequest book) {
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
