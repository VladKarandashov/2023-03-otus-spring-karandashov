package ru.otus.hw06booksapp.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw06booksapp.entity.Genre;
import ru.otus.hw06booksapp.repository.jpa.JpaGenreRepository;
import ru.otus.hw06booksapp.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
@Import({GenreServiceImpl.class, JpaGenreRepository.class})
class GenreServiceImplTest {

    private final static int EXPECTED_GENRES_COUNT = 4;
    private final static long GENRE_ONE_ID = 1L;
    private final static String GENRE_ONE_NAME = "Roman";
    private final static String GENRE_ONE_NAME_NEW = "Roman - New";

    @Autowired
    private GenreService genreService;

    @DisplayName("Should get correct Genre")
    @Test
    void shouldGetCorrectGenre() {
        Genre genre = genreService.getGenreById(GENRE_ONE_ID);
        assertEquals(GENRE_ONE_ID, genre.getId());
        assertEquals(GENRE_ONE_NAME, genre.getName());
    }

    @DisplayName("Should find all Genres")
    @Test
    void ShouldGetAllGenres() {
        val genres = genreService.getGenres();
        assertThat(genres).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }

    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        Genre genre = genreService.getGenreById(GENRE_ONE_ID);
        assertEquals(GENRE_ONE_NAME, genre.getName());
        // DELETE:
        genreService.delete(GENRE_ONE_ID);
        assertThatCode(() -> genreService.getGenreById(GENRE_ONE_ID))
                .isInstanceOf(RuntimeException.class);
    }
    
    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        long savedGenreId = genreService.create(GENRE_ONE_NAME_NEW);
        assertThat(savedGenreId).isGreaterThan(0);
        assertEquals(GENRE_ONE_NAME_NEW, genreService.getGenreById(savedGenreId).getName());
    }

}