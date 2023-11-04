package ru.otus.hw14booksapp.repository.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.mongo.AuthorMongo;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, String> {

    List<AuthorMongo> findAllBy();

    Optional<AuthorMongo> findById(String id);

    void delete(AuthorMongo author);
}