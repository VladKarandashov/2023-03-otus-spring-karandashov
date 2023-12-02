package ru.otus.hw11booksapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.utils.DtoConverter;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Тест на основе аннотации @WebFluxTest")
@WebFluxTest
@ContextConfiguration(classes = {BookController.class, DtoConverter.class})
class SpecialWebFluxTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    BookRepository bookRepository;

    @DisplayName("получение книги по id")
    @Test
    public void findAllAuthorsTest() throws JsonProcessingException {

        when(bookRepository.findById("1")).thenReturn(Mono.just(new Book("1", "title", new Author("author"), new Genre("genre"))));

        var body = webTestClient.get()
                .uri("/book/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().returnResult().getResponseBody();
        assertNotNull(body);
        var response = new String(body, StandardCharsets.UTF_8);
        var book = (new ObjectMapper()).readValue(response, BookDto.class);
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("genre", book.getGenre());
    }
}