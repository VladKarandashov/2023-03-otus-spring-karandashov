package ru.otus.hw10booksapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10booksapp.dto.BookDto;
import ru.otus.hw10booksapp.entity.Author;
import ru.otus.hw10booksapp.entity.Book;
import ru.otus.hw10booksapp.entity.Genre;
import ru.otus.hw10booksapp.service.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    private static final Author author = new Author(1L, "Person");
    private static final Genre genre = new Genre(1L, "Genre");
    private static final Book book = new Book(1L, author, genre, "book");
    private static final BookDto bookDto = new BookDto(1L, book.getTitle(), author.getName(), genre.getName());

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @Test
    void shouldReturnCorrectBookList() throws Exception {
        List<BookDto> bookList = List.of(bookDto);
        given(bookService.getAll()).willReturn(bookList);
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(bookDto))));
    }

    @Test
    void shouldReturnCorrectBook() throws Exception {
        given(bookService.getById(1)).willReturn(bookDto);
        mvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @Test
    void shouldCreateBook() throws Exception {
        given(bookService.create(any())).willReturn(bookDto);
        mvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        given(bookService.update(any(BookDto.class))).willReturn(bookDto);
        mvc.perform(put("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/book/1"))
                .andExpect(status().isOk());
    }
}
