package ru.otus.hw06booksapp.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw06booksapp.entity.Note;
import ru.otus.hw06booksapp.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoteRepositoryJpa implements NoteRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Note> getNoteById(long id) {
        return Optional.ofNullable(em.find(Note.class, id));
    }

    @Override
    public List<Note> getAllNote() {
        TypedQuery<Note> query = em.createQuery("select c from Note c", Note.class);
        return query.getResultList();
    }

    @Override
    public List<Note> getNoteByBookId(long bookId) {
        TypedQuery<Note> query = em.createQuery("SELECT r FROM Note r WHERE r.book.id = :bookId", Note.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public Note save(Note note) {
        if (note.getId() == 0) {
            em.persist(note);
            return note;
        }
        return em.merge(note);
    }


    @Override
    public long countNote() {
        return em.createQuery("select count(c) from Note c", Long.class).getSingleResult();
    }

    @Override
    public void delete(Note note) {
        em.remove(note);
    }


}