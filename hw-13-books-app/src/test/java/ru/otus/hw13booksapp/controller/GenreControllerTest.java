package ru.otus.hw13booksapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw13booksapp.entity.Genre;
import ru.otus.hw13booksapp.restcontroller.GenreController;
import ru.otus.hw13booksapp.service.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
public class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService genreService;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldReturnCorrectGenreList() throws Exception {
        List<Genre> genreList = List.of(new Genre(1L, "genre1"), new Genre(2L, "genre2"));
        given(genreService.getAll()).willReturn(genreList);

        mvc.perform(get("/api/v1/genre"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genreList)));
    }

    @Test
    void forbiddenGenreList() throws Exception {
        List<Genre> genreList = List.of(new Genre(1L, "genre1"), new Genre(2L, "genre2"));
        given(genreService.getAll()).willReturn(genreList);

        mvc.perform(get("/api/v1/genre"))
                .andExpect(status().isUnauthorized());
    }
}
