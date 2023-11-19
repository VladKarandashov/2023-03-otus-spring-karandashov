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
import ru.otus.hw11booksapp.repository.AuthorRepository;
import ru.otus.hw11booksapp.repository.BookRepository;
import ru.otus.hw11booksapp.repository.GenreRepository;
import ru.otus.hw11booksapp.repository.NoteRepository;
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
    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final DtoConverter dtoConverter;

    @Bean
    public RouterFunction<ServerResponse> composedRoutes() {
        return route()
                .GET("/book/{id}",
                        request -> bookRepository.findById(Long.parseLong(request.pathVariable("id")))
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
                                .flatMap(bookDto -> Mono.zip(
                                        Mono.just(bookDto.getTitle()),
                                        authorRepository.findByName(bookDto.getAuthor())
                                                .switchIfEmpty(Mono.error(new InternalError("Не найден автор для создания книги"))),
                                        genreRepository.findByName(bookDto.getGenre())
                                                .switchIfEmpty(Mono.error(new InternalError("Не найден жанр для создания книги")))
                                ))
                                .map(t -> dtoConverter.getBook(t.getT1(), t.getT2(), t.getT3()))
                                .flatMap(bookRepository::save)
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> created(request.uri()).contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(badRequest().build())
                )
                .PUT("/book", accept(APPLICATION_JSON),
                        request -> request.bodyToMono(UpdateRequest.class)
                                .flatMap(updateRequest -> Mono.zip(
                                        Mono.just(updateRequest.getId()),
                                        Mono.just(updateRequest.getTitle()),
                                        authorRepository.findByName(updateRequest.getAuthor())
                                                .map(Author::getId)
                                                .switchIfEmpty(Mono.error(new InternalError("Не найден автор для создания книги"))),
                                        genreRepository.findByName(updateRequest.getGenre())
                                                .map(Genre::getId)
                                                .switchIfEmpty(Mono.error(new InternalError("Не найден жанр для создания книги")))
                                ))
                                .map(t -> new Book(t.getT1(), t.getT3(), t.getT4(), t.getT2()))
                                .flatMap(bookRepository::save)
                                .flatMap(dtoConverter::getBookDto)
                                .flatMap(bookDto -> ok().contentType(APPLICATION_JSON).body(fromValue(bookDto)))
                                .switchIfEmpty(badRequest().build())
                )
                .DELETE("/book/{id}",
                        request -> Mono.just(Long.parseLong(request.pathVariable("id")))
                                .flatMap(id -> noteRepository.deleteAllByBookId(id).then(bookRepository.deleteById(id)))
                                .then(ok().build())
                )
                .build();
    }
}