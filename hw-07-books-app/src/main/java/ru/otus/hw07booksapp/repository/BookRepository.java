package ru.otus.hw07booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw07booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);

    List<Book> findAllBy();

    Long countBy();

    void delete(Book book);

    Book save(Book newBook);

}