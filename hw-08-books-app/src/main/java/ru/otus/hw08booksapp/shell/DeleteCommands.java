package ru.otus.hw08booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw08booksapp.service.AuthorService;
import ru.otus.hw08booksapp.service.BookService;
import ru.otus.hw08booksapp.service.NoteService;

@RequiredArgsConstructor
@ShellComponent
public class DeleteCommands {

    private final AuthorService authorService;

    private final BookService bookService;

    private final NoteService notesService;


    @ShellMethod(value = "delete author by id", key = {"delA"})
    public String deleteAuthorById(@ShellOption(defaultValue = "1") String authorId) {
        authorService.deleteById(authorId);
        return "Author with id=" + authorId + " was deleted";
    }

    @ShellMethod(value = "delete book by id", key = {"delB"})
    public String deleteBookById(@ShellOption(defaultValue = "1") String bookId) {
        bookService.deleteById(bookId);
        return "Book with bookId = " + bookId + " was deleted";
    }

    @ShellMethod(value = "delete books Note by id", key = {"delN"})
    public String deleteBooksNote(@ShellOption(defaultValue = "1") String noteId) {
        notesService.deleteById(noteId);
        return "Note with ID = " + noteId + " was deleted.";
    }

}