package ru.otus.hw13booksapp.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw13booksapp.entity.Genre;
import ru.otus.hw13booksapp.service.GenreService;

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
