package ru.otus.hw16booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw16booksapp.entity.Genre;
import ru.otus.hw16booksapp.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<Genre> genreList() {
        return genreService.getAll();
    }
}
