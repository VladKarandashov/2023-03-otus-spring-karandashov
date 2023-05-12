package ru.otus.hw06booksapp.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw06booksapp.entity.Book;
import ru.otus.hw06booksapp.exception.DaoException;
import ru.otus.hw06booksapp.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Optional<Book> getBookById(long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", em.getEntityGraph("book-author-genre"));
        return Optional.ofNullable(em.find(Book.class, id, properties));
    }


    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ", Book.class);
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("book-author-genre"));
        return query.getResultList();
    }


    @Override
    public Long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM Book b ", Long.class);
        return query.getSingleResult();
    }


    @Override
    public void deleteBook(Book book) {
        em.remove(book);
    }

    @Override
    public Book saveBook(Book newBook) {
        try {
            if (newBook.getId() == 0) {
                em.persist(newBook);
                return newBook;
            }
            return em.merge(newBook);
        } catch (Exception e) {
            throw new DaoException("Unexpected exception during book insertion.", e);
        }
    }

}