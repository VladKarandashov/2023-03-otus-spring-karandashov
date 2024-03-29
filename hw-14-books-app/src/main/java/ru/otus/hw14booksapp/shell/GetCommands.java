package ru.otus.hw14booksapp.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw14booksapp.entity.AuthorJpa;
import ru.otus.hw14booksapp.entity.BookJpa;
import ru.otus.hw14booksapp.entity.GenreJpa;
import ru.otus.hw14booksapp.repository.AuthorRepository;
import ru.otus.hw14booksapp.repository.BookRepository;
import ru.otus.hw14booksapp.repository.GenreRepository;

import java.util.stream.Collectors;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class GetCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @ShellMethod(value = "get author by id", key = {"getA"})
    public String getAuthorById(@ShellOption Integer id) {
        return authorRepository.findById(id)
                .map(AuthorJpa::toString)
                .orElse("not found");
    }

    @ShellMethod(value = "get all Authors", key = {"getAs"})
    public String getAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorJpa::toString)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get genre by id", key = {"getG"})
    public String getGenreById(@ShellOption Integer id) {
        return genreRepository.findById(id)
                .map(GenreJpa::toString)
                .orElse("not found");
    }

    @ShellMethod(value = "get all Genres", key = {"getGs"})
    public String getGenres() {
        return genreRepository.findAll().stream()
                .map(GenreJpa::toString)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get book by id", key = {"getB"})
    public String getBookById(@ShellOption Integer id) {
        return bookRepository.findById(id)
                .map(BookJpa::toString)
                .orElse("not found");
    }

    @ShellMethod(value = "get all Books", key = {"getBs"})
    public String getBooks() {
        return bookRepository.findAll().stream()
                .map(BookJpa::toString)
                .collect(Collectors.joining("\n"));
    }
}