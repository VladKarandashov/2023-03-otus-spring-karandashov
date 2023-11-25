package ru.otus.hw13booksapp.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.hw13booksapp.service.BookService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;

    @GetMapping("/library/{id}")
    public String editBook(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "adminArea/editBook";
    }
}