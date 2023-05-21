package ru.otus.hw07booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07booksapp.entity.Author;
import ru.otus.hw07booksapp.entity.Book;
import ru.otus.hw07booksapp.entity.Genre;
import ru.otus.hw07booksapp.entity.Note;
import ru.otus.hw07booksapp.service.AuthorService;
import ru.otus.hw07booksapp.service.BookService;
import ru.otus.hw07booksapp.service.GenreService;
import ru.otus.hw07booksapp.service.NoteService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GetCommands {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;


    @ShellMethod(value = "get author by id", key = {"getA"})
    public Author getAuthorById(@ShellOption(defaultValue = "1") long id) {
        return authorService.getById(id);
    }


    @ShellMethod(value = "get all Authors", key = {"getAs"})
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @ShellMethod(value = "get genre by id", key = {"getG"})
    public Genre getGenreById(@ShellOption(defaultValue = "1") long id) {
        return genreService.getGenreById(id);
    }

    @ShellMethod(value = "get all Genres", key = {"getGs"})
    public List<Genre> getAllGenres() {
         return genreService.getGenres();
    }

    @ShellMethod(value = "get book by id", key = {"getB"})
    public Book getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public List<Book> getBooks()  {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "get Note by id", key = {"getN"})
    public Note getNoteById(@ShellOption(defaultValue = "1") long id) {
        return notesService.getNoteById(id);
    }

    @ShellMethod(value = "get all notes", key = {"getNs"})
    public List<Note> getAllNote()  {
         return notesService.getAllNote();
    }

}