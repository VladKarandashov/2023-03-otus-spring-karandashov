package ru.otus.hw08booksapp.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw08booksapp.dto.BookDto;
import ru.otus.hw08booksapp.entity.Note;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ORM JPA books repository testing.")
@DataMongoTest
@Import({BookServiceImpl.class,
        AuthorServiceImpl.class,
        NoteServiceImpl.class})
class NoteServiceImplTest {

    private static final String MY_NOTE = "my note";
    private static final String MY_NOTE_1 = "my note 1";
    private static final String MY_NOTE_2 = "my note 2";
    private static final String DEL_NOTE = "del note";
    private static final String MY_BOOK = "my book";
    private static final String AUTHOR = "author";

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private NoteServiceImpl noteService;

    @DisplayName("Should get correct note")
    @Test
    void shouldGetCorrectNote() {
        var authorId = authorService.create(AUTHOR);
        var bookId = bookService.create(new BookDto(MY_BOOK, authorId, null));
        var id = noteService.create(bookId, MY_NOTE);
        Note note = noteService.getNoteById(id);
        Assertions.assertThat(note).isNotNull()
                .matches(n -> Objects.equals(n.getNote(), MY_NOTE));
    }

    @DisplayName("Should find all notes")
    @Test
    void ShouldFindAllNotes() {
        var authorId = authorService.create(AUTHOR);
        var bookId = bookService.create(new BookDto(MY_BOOK, authorId, null));
        noteService.create(bookId, MY_NOTE_1);
        noteService.create(bookId, MY_NOTE_2);
        List<Note> notes = noteService.getAllNote();
        assertTrue(notes.stream().anyMatch(note -> MY_NOTE_1.equals(note.getNote())));
        assertTrue(notes.stream().anyMatch(note -> MY_NOTE_2.equals(note.getNote())));
    }

    @DisplayName("Should be able to delete a note:")
    @Test
    void shouldDeleteNote() {
        var authorId = authorService.create(AUTHOR);
        var bookId = bookService.create(new BookDto(MY_BOOK, authorId, null));
        var id = noteService.create(bookId, DEL_NOTE);
        noteService.deleteById(id);
        var notes = noteService.getAllNote();
        assertTrue(notes.stream().noneMatch(note -> DEL_NOTE.equals(note.getNote())));
    }

}