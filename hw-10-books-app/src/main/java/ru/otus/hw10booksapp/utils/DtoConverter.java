package ru.otus.hw10booksapp.utils;

import lombok.experimental.UtilityClass;
import ru.otus.hw10booksapp.dto.AuthorDto;
import ru.otus.hw10booksapp.dto.BookDto;
import ru.otus.hw10booksapp.dto.GenreDto;
import ru.otus.hw10booksapp.dto.NoteDto;
import ru.otus.hw10booksapp.entity.Author;
import ru.otus.hw10booksapp.entity.Book;
import ru.otus.hw10booksapp.entity.Genre;
import ru.otus.hw10booksapp.entity.Note;

@UtilityClass
public class DtoConverter {

    public BookDto getBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName());
    }

    public AuthorDto getAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    public GenreDto getGenreDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public NoteDto getNoteDto(Note note) {
        return new NoteDto(note.getId(), note.getNote());
    }
}
