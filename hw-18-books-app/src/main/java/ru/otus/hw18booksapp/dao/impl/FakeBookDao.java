package ru.otus.hw18booksapp.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.otus.hw18booksapp.dao.repository.BookRepository;
import ru.otus.hw18booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Это ПЛОХАЯ реализация интерфейса BookDao, которая работает с вероятностью 50%.
 * Работает если включить в конфиге настройку
 */
@Slf4j
@Repository
@ConditionalOnProperty(prefix = "database", value = "problems", havingValue = "true")
public class FakeBookDao extends CorrectBookDao {

    public FakeBookDao(BookRepository bookRepository) {
        super(bookRepository);
    }

    @Override
    public Optional<Book> findById(long id) {
        if (fiftyFiftyChance()) {
            throw new RuntimeException("Не удалось достучаться до БД");
        }
        return super.findById(id);
    }

    @Override
    public List<Book> findAll() {
        if (fiftyFiftyChance()) {
            throw new RuntimeException("Не удалось достучаться до БД");
        }
        return super.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (fiftyFiftyChance()) {
            throw new RuntimeException("Не удалось достучаться до БД");
        }
        super.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        if (fiftyFiftyChance()) {
            throw new RuntimeException("Не удалось достучаться до БД");
        }
        return super.save(book);
    }

    private boolean fiftyFiftyChance() {
        return Math.random() < 0.5;
    }
}
