package ru.otus.hw06booksapp.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> getAllGenres() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();

    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }
}