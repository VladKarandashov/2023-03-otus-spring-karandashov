package ru.otus.hw14booksapp.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw14booksapp.entity.Author;
import ru.otus.hw14booksapp.entity.Book;
import ru.otus.hw14booksapp.entity.Genre;
import ru.otus.hw14booksapp.entity.Note;
import ru.otus.hw14booksapp.service.mongo.AuthorServiceMongo;
import ru.otus.hw14booksapp.service.mongo.BookServiceMongo;
import ru.otus.hw14booksapp.service.mongo.GenreServiceMongo;
import ru.otus.hw14booksapp.service.mongo.NoteServiceMongo;
import ru.otus.hw14booksapp.view.EntityFormatter;

import java.util.Map;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class GetCommandsMongo {
    private final AuthorServiceMongo authorService;

    private final GenreServiceMongo genreService;

    private final BookServiceMongo bookService;

    private final NoteServiceMongo notesService;

    // решение показалось очень красивым ;)
    private final Map<String, EntityFormatter<?>> formatters;

    @SneakyThrows
    @ShellMethod(value = "get author by id", key = {"mongo-getA"})
    public String getAuthorById(@ShellOption(defaultValue = "1") String id) {
        var formatter = (EntityFormatter<Author>) formatters.get("authorFormatter");
        return formatter.format(authorService.getById(id));
    }


    @ShellMethod(value = "get all Authors", key = {"mongo-getAs"})
    public String getAuthors() {
        var formatter = (EntityFormatter<Author>) formatters.get("authorFormatter");
        return formatter.format(authorService.getAll());
    }

    @ShellMethod(value = "get genre by id", key = {"mongo-getG"})
    public String getGenreById(@ShellOption(defaultValue = "1") String id) {
        var formatter = (EntityFormatter<Genre>) formatters.get("genreFormatter");
        return formatter.format(genreService.getById(id));
    }

    @ShellMethod(value = "get all Genres", key = {"mongo-getGs"})
    public String getAllGenres() {
        var formatter = (EntityFormatter<Genre>) formatters.get("genreFormatter");
        return formatter.format(genreService.getAll());
    }

    @ShellMethod(value = "get book by id", key = {"mongo-getB"})
    public String getBookById(@ShellOption(defaultValue = "1") String id) {
        var formatter = (EntityFormatter<Book>) formatters.get("bookFormatter");
        return formatter.format(bookService.getById(id));
    }

    @ShellMethod(value = "get all books", key = {"mongo-getBs"})
    public String getBooks() {
        var formatter = (EntityFormatter<Book>) formatters.get("bookFormatter");
        return formatter.format(bookService.getAll());
    }

    @ShellMethod(value = "get Note by id", key = {"mongo-getN"})
    public String getNoteById(@ShellOption(defaultValue = "1") String id) {
        var formatter = (EntityFormatter<Note>) formatters.get("noteFormatter");
        return formatter.format(notesService.getNoteById(id));
    }

    @ShellMethod(value = "get all notes", key = {"mongo-getNs"})
    public String getAllNote() {
        var formatter = (EntityFormatter<Note>) formatters.get("noteFormatter");
        return formatter.format(notesService.getAllNote());
    }

}