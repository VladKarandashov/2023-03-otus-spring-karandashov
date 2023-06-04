package ru.otus.hw10booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10booksapp.entity.Author;
import ru.otus.hw10booksapp.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> authorList() {
        return authorService.getAll();
    }
}
