package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.utils.DtoConverter;

@RequiredArgsConstructor
@Controller
public class PagesController {

    private final BookRepository bookRepository;
    private final DtoConverter dtoConverter;

    @GetMapping({"/"})
    public String listBooks() {
        return "books";
    }

    @GetMapping("/{id}")
    public Mono<String> editBook(@PathVariable(value = "id") String id, Model model) {
        return bookRepository.findById(id)
                .flatMap(dtoConverter::getBookDto)
                .map(book -> model.addAttribute("book", book))
                .map(updModel -> "book");
    }
}