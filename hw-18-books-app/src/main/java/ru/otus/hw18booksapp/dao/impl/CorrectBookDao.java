package ru.otus.hw18booksapp.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Repository;
import ru.otus.hw18booksapp.dao.BookDao;
import ru.otus.hw18booksapp.dao.repository.BookRepository;
import ru.otus.hw18booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Это "правильная" реализация интерфейса BookDao, которая всегда работает правильно
 * (по сути является прокси BookRepository).
 * Работает по умолчанию, если не включен FakeBookDao
 */
@Slf4j
@Repository
@RequiredArgsConstructor
@ConditionalOnMissingBean(FakeBookDao.class)
public class CorrectBookDao implements BookDao {

    private final BookRepository bookRepository;

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
