package ru.otus.hw16booksapp.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw16booksapp.container.base.BasePersistenceTest;
import ru.otus.hw16booksapp.dto.BookDto;
import ru.otus.hw16booksapp.entity.Author;
import ru.otus.hw16booksapp.entity.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
class ControllerTest extends BasePersistenceTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthors() throws Exception {

        var result = mockMvc.perform(get("/author"))
                .andReturn()
                .getResponse();

        var authorList = OBJECT_MAPPER.readValue(result.getContentAsString(), new TypeReference<List<Author>>() {});

        assertTrue(authorList.size() > 3);
    }

    @Test
    void getGenres() throws Exception {

        var result = mockMvc.perform(get("/genre"))
                .andReturn()
                .getResponse();

        var genreList = OBJECT_MAPPER.readValue(result.getContentAsString(), new TypeReference<List<Genre>>() {});

        assertTrue(genreList.size() > 3);
    }

    @Test
    void getBooks() throws Exception {

        var result = mockMvc.perform(get("/book"))
                .andReturn()
                .getResponse();

        var bookList = OBJECT_MAPPER.readValue(result.getContentAsString(), new TypeReference<List<BookDto>>() {});

        assertTrue(bookList.size() > 8);
    }
}