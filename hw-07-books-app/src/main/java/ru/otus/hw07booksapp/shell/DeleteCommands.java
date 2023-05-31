package ru.otus.hw07booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07booksapp.service.AuthorService;
import ru.otus.hw07booksapp.service.BookService;
import ru.otus.hw07booksapp.service.GenreService;
import ru.otus.hw07booksapp.service.NoteService;

@RequiredArgsConstructor
@ShellComponent
public class DeleteCommands {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;


    @ShellMethod(value = "delete author by id", key = {"delA"})
    public String deleteAuthorById(@ShellOption(defaultValue = "1") long authorId) {
        authorService.deleteById(authorId);
        return "Author with id=" + authorId + " was deleted";
    }

    @ShellMethod(value = "delete genre by id", key = {"delG"})
    public String deleteGenreById(@ShellOption(defaultValue = "1") long genreId) {
        genreService.deleteById(genreId);
        return "Genre with id = " + genreId + " was deleted";
    }

    @ShellMethod(value = "delete book by id", key = {"delB"})
    public String deleteBookById(@ShellOption(defaultValue = "1") long bookId) {
        bookService.deleteById(bookId);
        return "Book with bookId = " + bookId + " was deleted";
    }

    @ShellMethod(value = "delete books Note by id", key = {"delN"})
    public String deleteBooksNote(@ShellOption(defaultValue = "1") long noteId) {
        notesService.deleteById(noteId);
        return "Note with ID = " + noteId + " was deleted.";
    }

}