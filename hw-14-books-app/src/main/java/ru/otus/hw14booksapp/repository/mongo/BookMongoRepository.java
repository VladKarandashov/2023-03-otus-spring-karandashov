package ru.otus.hw14booksapp.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.mongo.BookMongo;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMongoRepository extends MongoRepository<BookMongo, String> {

    Optional<BookMongo> findById(String id);

    List<BookMongo> findAllBy();

    Long countBy();

    void delete(BookMongo book);

}