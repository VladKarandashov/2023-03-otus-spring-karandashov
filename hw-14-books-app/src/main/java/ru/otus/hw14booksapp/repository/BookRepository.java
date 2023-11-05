package ru.otus.hw14booksapp.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14booksapp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

	@EntityGraph(value = "bookWithGenreAndAuthor", type = EntityGraph.EntityGraphType.LOAD)
	Optional<Book> findById(Integer id);

	@EntityGraph(value = "bookWithGenreAndAuthor", type = EntityGraph.EntityGraphType.LOAD)
	List<Book> findAll();

}
