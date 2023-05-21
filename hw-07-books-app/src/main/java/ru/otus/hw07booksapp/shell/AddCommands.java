package ru.otus.hw07booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07booksapp.dto.BookDto;
import ru.otus.hw07booksapp.service.AuthorService;
import ru.otus.hw07booksapp.service.BookService;
import ru.otus.hw07booksapp.service.GenreService;
import ru.otus.hw07booksapp.service.NoteService;

@ShellComponent
@RequiredArgsConstructor
public class AddCommands {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final NoteService notesService;


    @ShellMethod(value = "add author, param: String fullName", key = {"addA"})
    public void addAuthor(@ShellOption(defaultValue = "new Author") String fullName) {
        authorService.create(fullName);
    }


    @ShellMethod(value = "add genre, param: String name", key = {"addG"})
    public void addGenre(@ShellOption(defaultValue = "New genre") String name) {
        genreService.create(name);
    }


    @ShellMethod(value = "add book, param: String title, Long authorId, genreId", key = {"addB"})
    public void addBook(@ShellOption(defaultValue = "new Title") String title,
                        @ShellOption(defaultValue = "1") long authorId,
                        @ShellOption(defaultValue = "1") long genreId) {
        BookDto bookDto = new BookDto(title, authorId, genreId);
        bookService.createBook(bookDto);
    }


    @ShellMethod(value = "add books Note, param: Long bookId, String noteContext", key = {"addN"})
    public String addNewNote(@ShellOption(defaultValue = "1") long bookId,
                             @ShellOption(defaultValue = "new note: 'Good Book'") String noteContext) {
        notesService.create(bookId, noteContext + " - " + bookId);
        return "New note was create.";
    }

}