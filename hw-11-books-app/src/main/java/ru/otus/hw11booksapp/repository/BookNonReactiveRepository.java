package ru.otus.hw11booksapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw11booksapp.entity.Book;

/**
 * необходим для Mongock
 * <a href="https://docs.mongock.io/v4/reactive/index.html">...</a>
 */
@Repository
public interface BookNonReactiveRepository extends MongoRepository<Book, String> {

}