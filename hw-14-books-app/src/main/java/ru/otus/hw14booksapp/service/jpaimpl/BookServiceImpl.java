package ru.otus.hw14booksapp.service.jpaimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.Book;
import ru.otus.hw14booksapp.entity.jpa.BookJpa;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.jpa.BookRepository;
import ru.otus.hw14booksapp.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public BookJpa getById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAllBy().stream()
                .map(bookJpa -> (Book) bookJpa)
                .collect(Collectors.toList());
    }
}