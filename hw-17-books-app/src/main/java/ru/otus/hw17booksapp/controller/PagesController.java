package ru.otus.hw17booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw17booksapp.service.BookService;

@RequiredArgsConstructor
@Controller
public class PagesController {

    private final BookService bookService;

    @GetMapping({"/"})
    public String listBooks() {
        return "books";
    }

    @GetMapping("/{id}")
    public String editBook(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "book";
    }
}