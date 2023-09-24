package ru.otus.hw08booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08booksapp.dto.BookDto;
import ru.otus.hw08booksapp.entity.Book;
import ru.otus.hw08booksapp.entity.Genre;
import ru.otus.hw08booksapp.exception.NotFoundException;
import ru.otus.hw08booksapp.repository.BookRepository;
import ru.otus.hw08booksapp.repository.NoteRepository;
import ru.otus.hw08booksapp.service.AuthorService;
import ru.otus.hw08booksapp.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    @Override
    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {
        return bookRepository.countBy();
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        // удаляем комментарии к книге
        noteRepository.deleteAllByBook_Id(id);
        // удаляем саму книгу
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        if (book == null) {
            throw new NotFoundException(BOOK_NOT_EXIST);
        }
        try {
            return bookRepository.save(book);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(BOOK_NOT_EXIST, e);
        }
    }

    @Transactional
    @Override
    public String create(BookDto bookDto) {
        var author = authorService.getById(bookDto.getAuthorId());
        var genre = new Genre(bookDto.getGenreName());
        Book book = new Book(null, author, genre, bookDto.getTitle());
        return update(book).getId();
    }
}