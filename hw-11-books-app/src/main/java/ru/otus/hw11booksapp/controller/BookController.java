package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.dto.request.UpdateRequest;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.service.BookService;
import ru.otus.hw11booksapp.utils.DtoConverter;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final DtoConverter dtoConverter;

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(BookService bookService) {
        return route()
                .GET("/book/{id}",
                        request -> bookRepository.findById(Long.parseLong(request.pathVariable("id")))
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(notFound().build())
                )
                .GET("/book",
                        request -> bookService.getAll().collectList()
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(notFound().build())
                )
                .POST("/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(BookDto.class)
                                .flatMap(bookService::create)
                                .flatMap(bookDto -> created(request.uri()).contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(badRequest().build())
                )
                .PUT("/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(UpdateRequest.class)
                                .map(bookService::update)
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).build())
                                .switchIfEmpty(badRequest().build())
                )
                .DELETE("/book/{id}",
                        request -> bookService.deleteById(Long.parseLong(request.pathVariable("id")))
                                .transform(bookDto -> ok().build())
                                .switchIfEmpty(noContent().build())
                )
                .build();
    }
}