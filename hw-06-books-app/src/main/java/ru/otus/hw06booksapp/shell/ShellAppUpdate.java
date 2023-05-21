package ru.otus.hw06booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.service.AuthorService;
import ru.otus.hw06booksapp.service.BookService;
import ru.otus.hw06booksapp.service.GenreService;
import ru.otus.hw06booksapp.service.NoteService;

@RequiredArgsConstructor
@ShellComponent
public class ShellAppUpdate {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final NoteService notesService;

    @ShellMethod(value = "update author, param: long id, String newName", key = {"updA"})
    public void updateAuthorName(@ShellOption(defaultValue = "1") long id,
                                 @ShellOption(defaultValue = "New authour-1") String newName) {
        authorService.update(id, newName);
        System.out.println("Author updates. New author: ");
    }

    @ShellMethod(value = "update genre, param: long id, String newName", key = {"updG"})
    public void updateGenreName(@ShellOption(defaultValue = "1") long id,
                                @ShellOption(defaultValue = "new Genre name-1") String newName) {
        genreService.update(id, newName);
        System.out.println("Genre updated");
    }

    @ShellMethod(value = "update book, param: long id, String newName", key = {"updB"})
    public void updateBookTitle(@ShellOption(defaultValue = "1") long id,
                               @ShellOption(defaultValue = "new cool book title-1") String newName) {
        Book book = bookService.getBookById(id);
        book.setTitle(newName);
        bookService.createBook(book);
        System.out.println("book updated. New book title: " + book.getTitle());
    }

    @ShellMethod(value = "update note, param: long id, String newContext", key = {"updN"})
    public void updateNoteContext(@ShellOption(defaultValue = "1") long id,
                                    @ShellOption(defaultValue = "new cool note context-1") String newContext) {
        notesService.update(id, newContext);
        System.out.println("Note context was updated.");
    }

}