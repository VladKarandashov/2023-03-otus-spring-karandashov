package ru.otus.hw06booksapp.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.repository.jpa.JpaGenreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
@Import(JpaGenreRepository.class)
class JpaGenreRepositoryTest {

    private final static int EXPECTED_GENRES_COUNT = 4;
    private final static long GENRE_ONE_ID = 1L;
    private final static String GENRE_ONE_NAME = "Roman";
    private final static String GENRE_ONE_NAME_NEW = "Roman - New";

    @Autowired
    private JpaGenreRepository jpaGenreRepository;

    @DisplayName("Should get correct Genre")
    @Test
    void shouldGetCorrectGenre() {
        Optional<Genre> genre = jpaGenreRepository.getGenreById(GENRE_ONE_ID);
        assertEquals(GENRE_ONE_ID, genre.get().getId());
        assertEquals(GENRE_ONE_NAME, genre.get().getName());
    }

    @DisplayName("Should find all Genres")
    @Test
    void ShouldGetAllGenres() {
        val genres = jpaGenreRepository.getAllGenres();
        assertThat(genres).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }

    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        Genre genre = jpaGenreRepository.getGenreById(GENRE_ONE_ID).get();
        assertEquals(GENRE_ONE_NAME, genre.getName());
        // DELETE:
        jpaGenreRepository.delete(genre);
        Optional<Genre> GenreOptional = jpaGenreRepository.getGenreById(GENRE_ONE_ID);
        assertEquals(Optional.empty(), GenreOptional);
    }
    
    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        Genre genre = new Genre(0L, GENRE_ONE_NAME_NEW);
        Genre savedGenre = jpaGenreRepository.save(genre);
        assertThat(savedGenre.getId()).isGreaterThan(0);
        assertEquals(GENRE_ONE_NAME_NEW, savedGenre.getName());
    }

}