package ru.otus.hw11booksapp.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.repository.GenreRepository;

@DisplayName("Тест на основе аннотации @WebFluxTest")
@WebFluxTest
@ContextConfiguration(classes = {AuthorController.class, GenreController.class})
class SpecialWebFluxTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;

    @DisplayName("список авторов")
    @Test
    public void findAllAuthorsTest() {
        webTestClient.get()
                .uri("/author")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("список жанров")
    @Test
    public void findAllGenresTest() {
        webTestClient.get()
                .uri("/genre")
                .exchange()
                .expectStatus()
                .isOk();
    }
}