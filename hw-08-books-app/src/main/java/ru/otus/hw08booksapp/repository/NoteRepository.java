package ru.otus.hw08booksapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw08booksapp.entity.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Optional<Note> findById(String id);

    List<Note> findAllBy();

    List<Note> findAllByBook_Id(String bookId);

    long countBy();

    void delete(Note note);

    void deleteAllByBook_Id(String bookId);
}