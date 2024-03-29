package ru.otus.hw09booksapp.utils;

import lombok.experimental.UtilityClass;
import ru.otus.hw09booksapp.dto.AuthorDto;
import ru.otus.hw09booksapp.dto.BookCompleteDto;
import ru.otus.hw09booksapp.dto.BookDto;
import ru.otus.hw09booksapp.dto.GenreDto;
import ru.otus.hw09booksapp.dto.NoteDto;
import ru.otus.hw09booksapp.entity.Author;
import ru.otus.hw09booksapp.entity.Book;
import ru.otus.hw09booksapp.entity.Genre;
import ru.otus.hw09booksapp.entity.Note;

@UtilityClass
public class DtoConverter {

    public BookDto getBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName());
    }

    public BookCompleteDto getCompleteBookDto(Book book) {
        return new BookCompleteDto(book.getId(), book.getTitle(),
                getAuthorDto(book.getAuthor()),
                getGenreDto(book.getGenre()));
    }

    public Book getBook(String title, Author author, Genre genre) {
        return new Book(null, author, genre, title);
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
