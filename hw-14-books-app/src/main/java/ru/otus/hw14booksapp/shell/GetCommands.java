package ru.otus.hw14booksapp.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw14booksapp.entity.jpa.Author;
import ru.otus.hw14booksapp.entity.jpa.Book;
import ru.otus.hw14booksapp.entity.jpa.Genre;
import ru.otus.hw14booksapp.entity.jpa.Note;
import ru.otus.hw14booksapp.service.AuthorService;
import ru.otus.hw14booksapp.service.BookService;
import ru.otus.hw14booksapp.service.GenreService;
import ru.otus.hw14booksapp.service.NoteService;
import ru.otus.hw14booksapp.view.EntityFormatter;

import java.util.Map;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class GetCommands {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;

    // решение показалось очень красивым ;)
    private final Map<String, EntityFormatter<?>> formatters;

    @SneakyThrows
    @ShellMethod(value = "get author by id", key = {"getA"})
    public String getAuthorById(@ShellOption(defaultValue = "1") long id) {
        var formatter = (EntityFormatter<Author>) formatters.get("authorFormatter");
        return formatter.format(authorService.getById(id));
    }


    @ShellMethod(value = "get all Authors", key = {"getAs"})
    public String getAuthors() {
        var formatter = (EntityFormatter<Author>) formatters.get("authorFormatter");
        return formatter.format(authorService.getAll());
    }

    @ShellMethod(value = "get genre by id", key = {"getG"})
    public String getGenreById(@ShellOption(defaultValue = "1") long id) {
        var formatter = (EntityFormatter<Genre>) formatters.get("genreFormatter");
        return formatter.format(genreService.getById(id));
    }

    @ShellMethod(value = "get all Genres", key = {"getGs"})
    public String getAllGenres() {
        var formatter = (EntityFormatter<Genre>) formatters.get("genreFormatter");
        return formatter.format(genreService.getAll());
    }

    @ShellMethod(value = "get book by id", key = {"getB"})
    public String getBookById(@ShellOption(defaultValue = "1") long id) {
        var formatter = (EntityFormatter<Book>) formatters.get("bookFormatter");
        return formatter.format(bookService.getById(id));
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public String getBooks() {
        var formatter = (EntityFormatter<Book>) formatters.get("bookFormatter");
        return formatter.format(bookService.getAll());
    }

    @ShellMethod(value = "get Note by id", key = {"getN"})
    public String getNoteById(@ShellOption(defaultValue = "1") long id) {
        var formatter = (EntityFormatter<Note>) formatters.get("noteFormatter");
        return formatter.format(notesService.getNoteById(id));
    }

    @ShellMethod(value = "get all notes", key = {"getNs"})
    public String getAllNote() {
        var formatter = (EntityFormatter<Note>) formatters.get("noteFormatter");
        return formatter.format(notesService.getAllNote());
    }

}