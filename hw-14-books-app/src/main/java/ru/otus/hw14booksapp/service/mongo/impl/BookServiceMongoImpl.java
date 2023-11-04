package ru.otus.hw14booksapp.service.mongo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw14booksapp.entity.Book;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.mongo.BookMongoRepository;
import ru.otus.hw14booksapp.service.mongo.BookServiceMongo;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceMongoImpl implements BookServiceMongo {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookMongoRepository bookRepository;

    @Override
    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAllBy().stream()
                .map(bookMongo -> (Book) bookMongo)
                .collect(Collectors.toList());
    }
}