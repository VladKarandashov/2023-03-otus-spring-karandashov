package ru.otus.hw09booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw09booksapp.dto.BookCompleteDto;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.service.AuthorService;
import ru.otus.hw09booksapp.service.BookService;
import ru.otus.hw09booksapp.service.GenreService;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    @GetMapping({"/book", "/"})
    public String listBooks(Model model) {
        var books = bookService.getAll();
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "books";
    }

    @GetMapping("/book/{id}")
    public String editBook(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("book", bookService.getCompleteById(id));
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "book";
    }

    @PostMapping("/book/{id}")
    public String saveBook(BookCompleteDto book) {
        var saveBook = bookService.update(book);
        return "redirect:/book/" + saveBook.getId();
    }

    @PostMapping("/book/delete")
    public String delete(@RequestParam("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @PostMapping("/book/create")
    public String create(BookDto book) {
        bookService.create(book);
        return "redirect:/book";
    }
}