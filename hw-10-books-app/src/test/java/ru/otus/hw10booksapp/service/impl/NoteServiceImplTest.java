package ru.otus.hw10booksapp.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw10booksapp.entity.Author;
import ru.otus.hw10booksapp.entity.Book;
import ru.otus.hw10booksapp.entity.Genre;
import ru.otus.hw10booksapp.entity.Note;
import ru.otus.hw10booksapp.repository.NoteRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
class NoteServiceImplTest {

    private final static int EXPECTED_NOTES_COUNT = 1;
    private final static long NOTE_ONE_ID = 1L;
    private final static String NOTE_ONE_CONTEXT = "Note-01.1 - Master";
    private final static String NOTE_ONE_CONTEXT_NEW = "Note-01.1 - Master - New";
    private final static Author AUTHOR_ONE = new Author(1L, "Михаил Булгаков");
    private final static Genre GENRE_ONE = new Genre(1L, "Roman");
    private final static String BOOK_ONE_NAME = "MasterTest";
    private final static Book BOOK_ONE = new Book(1L, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);

    @Autowired
    private NoteRepository noteRepository;


    @DisplayName("Should get correct Note by ID")
    @Test
    void shouldGetCorrectGenre() {
        Optional<Note> genre = noteRepository.findById(NOTE_ONE_ID);
        assertEquals(NOTE_ONE_ID, genre.get().getId());
        assertEquals(NOTE_ONE_CONTEXT, genre.get().getNote());
    }

    @DisplayName("Should find all Note")
    @Test
    void ShouldGetAllGenres() {
        val genres = noteRepository.findAllBy();
        assertThat(genres).isNotNull().hasSize(EXPECTED_NOTES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getNote().equals(""));
    }

    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        Optional<Note> note = noteRepository.findById(NOTE_ONE_ID);
        assertNotNull(note.get());
        assertEquals(NOTE_ONE_CONTEXT, note.get().getNote());
        // DELETE:
        noteRepository.delete(note.get());
        Optional<Note> noteAfterDelete = noteRepository.findById(NOTE_ONE_ID);
        assertEquals(Optional.empty(), noteAfterDelete);
    }

    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        Note note = new Note(0L, BOOK_ONE, NOTE_ONE_CONTEXT_NEW);
        Note savedNote = noteRepository.save(note);
        assertThat(savedNote.getId()).isGreaterThan(0);
        assertEquals(NOTE_ONE_CONTEXT_NEW, savedNote.getNote());
    }

}