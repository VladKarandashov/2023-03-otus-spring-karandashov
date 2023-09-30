package ru.otus.hw08booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw08booksapp.entity.Book;
import ru.otus.hw08booksapp.service.AuthorService;
import ru.otus.hw08booksapp.service.BookService;
import ru.otus.hw08booksapp.service.NoteService;

@RequiredArgsConstructor
@ShellComponent
public class UpdateCommands {

    private final AuthorService authorService;

    private final BookService bookService;

    private final NoteService notesService;

    @ShellMethod(value = "update author, param: long id, String newName", key = {"updA"})
    public String updateAuthorName(@ShellOption(defaultValue = "1") String id,
                                   @ShellOption(defaultValue = "New authour-1") String newName) {
        authorService.update(id, newName);
        return "Author updated";
    }

    @ShellMethod(value = "update book, param: long id, String newName", key = {"updB"})
    public String updateBookTitle(@ShellOption(defaultValue = "1") String id,
                                  @ShellOption(defaultValue = "new cool book title-1") String newName) {
        Book book = bookService.getById(id);
        book.setTitle(newName);
        bookService.update(book);
        return "Book updated";
    }

    @ShellMethod(value = "update note, param: long id, String newContext", key = {"updN"})
    public String updateNoteContext(@ShellOption(defaultValue = "1") String id,
                                    @ShellOption(defaultValue = "new cool note context-1") String newContext) {
        notesService.update(id, newContext);
        return "Note updated";
    }

}