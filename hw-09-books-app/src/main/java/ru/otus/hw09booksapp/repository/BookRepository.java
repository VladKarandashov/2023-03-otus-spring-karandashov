package ru.otus.hw09booksapp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw09booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre")
    Optional<Book> findById(long id);

    @EntityGraph(value = "book-author-genre")
    List<Book> findAllBy();

    Long countBy();

    void delete(Book book);

    Book save(Book newBook);

}