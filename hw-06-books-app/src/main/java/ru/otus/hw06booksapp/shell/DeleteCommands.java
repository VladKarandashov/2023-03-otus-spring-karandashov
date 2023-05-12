package ru.otus.hw06booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw06booksapp.service.AuthorService;
import ru.otus.hw06booksapp.service.BookService;
import ru.otus.hw06booksapp.service.GenreService;
import ru.otus.hw06booksapp.service.NoteService;

@RequiredArgsConstructor
@ShellComponent
public class DeleteCommands {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;


    @ShellMethod(value = "delete author", key = {"delA"})
    public void deleteAuthorById(@ShellOption(defaultValue = "1") long authorId) {
        authorService.delete(authorId);
        System.out.println("Author with id=" + authorId + " deleted.");
    }

    @ShellMethod(value = "delete genre", key = {"delG"})
    public void deleteGenreById(@ShellOption(defaultValue = "1") long genreId) {
        genreService.delete(genreId);
        System.out.println("Genre with id = " + genreId + " was deleted");
    }

    @ShellMethod(value = "delete book", key = {"delB"})
    public void deleteBookById(@ShellOption(defaultValue = "1") long bookId) {
        bookService.deleteBook(bookId);
        System.out.println("Book with bookId = " + bookId + " was deleted");
    }

    @ShellMethod(value = "delete books Note", key = {"delN"})
    public void deleteBooksNote(@ShellOption(defaultValue = "1") long noteId) {
        notesService.delete(noteId);
        System.out.println("Note with ID = " + noteId + " was deleted.");
    }

}