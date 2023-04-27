package ru.otus.hw05booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05booksapp.service.BookService;

@ShellComponent
@RequiredArgsConstructor
@ShellCommandGroup("Commands for Books")
public class BookCommands {

    private final BookService bookService;

    private String userName;

    @ShellMethod(value = "Authorization to use the library.", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String userName) {
        this.userName = userName;
        return String.format("%s, welcome to the library.", this.userName);
    }


    @ShellMethod(value = "Get count", key = {"c", "count"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getCount() {
        return String.valueOf(bookService.getCount());
    }


    @ShellMethod(value = "Get all", key = {"a", "all"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getAll() {
        return bookService.findAll().toString();
    }


    @ShellMethod(value = "Get By Id", key = {"id", "bookById"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getById(@ShellOption long id) {
        return bookService.findById(id).toString();
    }


    @ShellMethod(value = "Delete By Id", key = {"d", "dellById"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String deleteById(@ShellOption long id) {
        bookService.deleteById(id);
        return "Book deleted";
    }


    @ShellMethod(value = "Update book title by ID (bookId, newTitle)", key = {"u", "update"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String updateById(@ShellOption long id, @ShellOption String newTitle) {
        return bookService.updateTitleById(id, newTitle).toString();
    }


    @ShellMethod(value = "Insert new book (authorId genreId bookTitle)", key = {"in", "insert"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String insert(@ShellOption long authorId, @ShellOption long genreId, @ShellOption String bookTitle) {
        return bookService.insert(authorId, genreId, bookTitle).toString();
    }

    private Availability isAuthorized() {
        return this.userName == null ? Availability.unavailable("Log in first") : Availability.available();
    }

}