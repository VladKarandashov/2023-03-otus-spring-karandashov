package ru.otus.hw14booksapp.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14booksapp.entity.mongo.NoteMongo;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteMongoRepository extends MongoRepository<NoteMongo, String> {

    Optional<NoteMongo> findById(String id);

    List<NoteMongo> findAllBy();

    List<NoteMongo> findAllByBook_Id(String bookId);

    long countBy();

    void delete(NoteMongo note);

    void deleteAllByBook_Id(String bookId);
}