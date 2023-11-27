package ru.otus.hw11booksapp.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("Тест на основе аннотации @WebFluxTest")
@WebFluxTest
@ContextConfiguration(classes = {AuthorController.class, GenreController.class})
class SpecialWebFluxTest {

    @Autowired
    private WebTestClient webTestClient;

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