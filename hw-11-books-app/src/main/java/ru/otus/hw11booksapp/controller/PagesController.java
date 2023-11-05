package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.service.BookService;

@RequiredArgsConstructor
@Controller
public class PagesController {

    private final BookService bookService;

    @GetMapping({"/"})
    public String listBooks() {
        return "books";
    }

    @GetMapping("/{id}")
    public Mono<String> editBook(@PathVariable(value = "id") long id, Model model) {
        return bookService.getById(id)
                .map(book -> model.addAttribute("book", book))
                .map(updModel -> "book");
    }
}