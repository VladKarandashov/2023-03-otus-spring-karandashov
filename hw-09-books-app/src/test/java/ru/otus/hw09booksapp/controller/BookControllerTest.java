package ru.otus.hw09booksapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw09booksapp.dto.AuthorDto;
import ru.otus.hw09booksapp.dto.BookCompleteDto;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.dto.GenreDto;
import ru.otus.hw09booksapp.entity.Author;
import ru.otus.hw09booksapp.entity.Genre;
import ru.otus.hw09booksapp.service.AuthorService;
import ru.otus.hw09booksapp.service.BookService;
import ru.otus.hw09booksapp.service.GenreService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void prepareMocks() {
        reset(bookService);
        reset(authorService);
        reset(genreService);
        List<BookDto> books = List.of(
                new BookDto(1L, "title1", "author1", "genre1"),
                new BookDto(2L, "title2", "author2", "genre2")
        );
        given(bookService.getAll()).willReturn(books);
        given(bookService.getCompleteById(anyLong())).willReturn(
                new BookCompleteDto(
                        1L,
                        "title1",
                        new AuthorDto(1L, "author1"),
                        new GenreDto(1L, "genre1")
                )
        );
        given(bookService.update(any(BookCompleteDto.class))).willReturn(books.get(0));
        List<Author> authors = List.of(
                new Author(1L, "author1"),
                new Author(2L, "author2")
        );
        given(authorService.getAll()).willReturn(authors);
        List<Genre> genres = List.of(
                new Genre(1L, "genre1"),
                new Genre(2L, "genre2")
        );
        given(genreService.getAll()).willReturn(genres);
    }

    @Test
    void listBooksTest() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isOk());
        mvc.perform(get("/"))
                .andExpect(status().isOk());
        verify(bookService, times(2)).getAll();
        verify(authorService, times(2)).getAll();
        verify(genreService, times(2)).getAll();
    }

    @Test
    void editBookTest() throws Exception {
        mvc.perform(get("/book/1"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).getCompleteById(1L);
        verify(authorService, times(1)).getAll();
        verify(genreService, times(1)).getAll();
    }

    @Test
    void deleteTest() throws Exception {
        mvc.perform(post("/book/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).deleteById(anyLong());
    }
}