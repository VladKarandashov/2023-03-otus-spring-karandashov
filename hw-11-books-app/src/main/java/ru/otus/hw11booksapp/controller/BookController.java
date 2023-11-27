package ru.otus.hw11booksapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.hw11booksapp.dto.BookDto;
import ru.otus.hw11booksapp.dto.request.UpdateRequest;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.BookRepository;
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
    public RouterFunction<ServerResponse> composedRoutes() {
        return route()
                .GET("/book/{id}",
                        request -> bookRepository.findById(request.pathVariable("id"))
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(notFound().build())
                )
                .GET("/book",
                        request -> bookRepository.findAll()
                                .flatMap(dtoConverter::getBookDto)
                                .collectList()
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(notFound().build())
                )
                .POST("/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(BookDto.class)
                                .map(bookDto -> dtoConverter.getBook(bookDto.getTitle(), new Author(bookDto.getAuthor()), new Genre(bookDto.getGenre())))
                                .flatMap(bookRepository::save)
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> created(request.uri()).contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(badRequest().build())
                )
                .PUT("/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(UpdateRequest.class)
                                .map(updateRequest -> new Book(updateRequest.getId(), updateRequest.getTitle(), new Author(updateRequest.getAuthor()), new Genre(updateRequest.getGenre())))
                                .flatMap(bookRepository::save)
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(badRequest().build())
                )
                .DELETE("/book/{id}",
                        request -> Mono.just(request.pathVariable("id"))
                                .flatMap(bookRepository::deleteById)
                                .then(ok().build())
                )
                .build();
    }
}