package ru.otus.hw07booksapp.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07booksapp.service.AuthorService;
import ru.otus.hw07booksapp.service.BookService;
import ru.otus.hw07booksapp.service.GenreService;
import ru.otus.hw07booksapp.service.NoteService;
import ru.otus.hw07booksapp.view.EntityFormatter;

import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class GetCommands {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;

    // решение показалось очень красивым ;)
    private final Map<String, EntityFormatter<Object>> formatters;

    @SneakyThrows
    @ShellMethod(value = "get author by id", key = {"getA"})
    public String getAuthorById(@ShellOption(defaultValue = "1") long id) {
        var formatter = formatters.get("AuthorFormatter");
        return formatter.format(authorService.getById(id));
    }


    @ShellMethod(value = "get all Authors", key = {"getAs"})
    public String getAuthors() {
        var formatter = formatters.get("AuthorFormatter");
        return formatter.format(authorService.getAll());
    }

    @ShellMethod(value = "get genre by id", key = {"getG"})
    public String getGenreById(@ShellOption(defaultValue = "1") long id) {
        var formatter = formatters.get("GenreFormatter");
        return formatter.format(genreService.getById(id));
    }

    @ShellMethod(value = "get all Genres", key = {"getGs"})
    public String getAllGenres() {
        var formatter = formatters.get("GenreFormatter");
        return formatter.format(genreService.getAll());
    }

    @ShellMethod(value = "get book by id", key = {"getB"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        var formatter = formatters.get("BookFormatter");
        return formatter.format(bookService.getById(id));
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public String getBooks()  {
        var formatter = formatters.get("BookFormatter");
        return formatter.format(bookService.getAll());
    }

    @ShellMethod(value = "get Note by id", key = {"getN"})
    public String getNoteById(@ShellOption(defaultValue = "1") long id) {
        var formatter = formatters.get("NoteFormatter");
        return formatter.format(notesService.getNoteById(id));
    }

    @ShellMethod(value = "get all notes", key = {"getNs"})
    public String getAllNote()  {
        var formatter = formatters.get("NoteFormatter");
        return formatter.format(notesService.getAllNote());
    }

}