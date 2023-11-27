package ru.otus.hw11booksapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@TestPropertySource(properties = "de.flapdoodle.mongodb.embedded.version=4.0.2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnnotatedControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void getAllBooks() {
        //given
        var client = WebClient.create(String.format("http://localhost:%d", port));

        //when
        var result = client.get()
                .uri("/book")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(3)).block();

        //then
        assert result != null;
        Assertions.assertEquals(JsonNodeType.ARRAY, result.getNodeType());
        Assertions.assertFalse(result.isEmpty());
    }
}