package ru.otus.hw08booksapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw08booksapp.entity.Author;


import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAllBy();

    Optional<Author> findById(String id);

    void delete(Author author);
}