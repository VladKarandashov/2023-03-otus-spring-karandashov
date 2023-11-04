package ru.otus.hw14booksapp.repository.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.mongo.GenreMongo;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreMongoRepository extends MongoRepository<GenreMongo, String> {

    List<GenreMongo> findAllBy();

    Optional<GenreMongo> findById(String id);
}