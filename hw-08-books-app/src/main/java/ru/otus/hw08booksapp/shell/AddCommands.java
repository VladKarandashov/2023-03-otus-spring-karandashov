package ru.otus.hw08booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw08booksapp.dto.BookDto;
import ru.otus.hw08booksapp.service.AuthorService;
import ru.otus.hw08booksapp.service.BookService;
import ru.otus.hw08booksapp.service.NoteService;

@ShellComponent
@RequiredArgsConstructor
public class AddCommands {
    private final BookService bookService;

    private final AuthorService authorService;

    private final NoteService notesService;


    @ShellMethod(value = "add author, param: String fullName", key = {"addA"})
    public String addAuthor(@ShellOption(defaultValue = "new Author") String fullName) {
        return authorService.create(fullName);
    }


    @ShellMethod(value = "add book, param: String title, Long authorId, genreId", key = {"addB"})
    public String addBook(@ShellOption(defaultValue = "new Title") String title,
                        @ShellOption(defaultValue = "1") String authorId,
                        @ShellOption(defaultValue = "n/a") String genreName) {
        BookDto bookDto = new BookDto(title, authorId, genreName);
        return bookService.create(bookDto);
    }


    @ShellMethod(value = "add books Note, param: Long bookId, String noteContext", key = {"addN"})
    public String addNewNote(@ShellOption(defaultValue = "1") String bookId,
                           @ShellOption(defaultValue = "new note: 'Good Book'") String noteContext) {
        return notesService.create(bookId, noteContext);
    }

}