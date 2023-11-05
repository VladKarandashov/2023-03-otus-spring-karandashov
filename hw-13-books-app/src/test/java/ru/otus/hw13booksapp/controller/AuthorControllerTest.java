package ru.otus.hw13booksapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw13booksapp.entity.Author;
import ru.otus.hw13booksapp.restcontroller.AuthorController;
import ru.otus.hw13booksapp.service.AuthorService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(
        username = "admin",
        authorities = {"ROLE_ADMIN"}
)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService authorService;

    @Test
    void shouldReturnCorrectAuthorList() throws Exception {
        List<Author> authorList = List.of(new Author(1L, "Person1"), new Author(2L, "Person2"));
        given(authorService.getAll()).willReturn(authorList);

        mvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(authorList)));
    }
}
