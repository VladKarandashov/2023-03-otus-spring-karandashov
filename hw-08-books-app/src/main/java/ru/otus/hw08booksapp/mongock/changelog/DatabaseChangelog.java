package ru.otus.hw08booksapp.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.hw08booksapp.entity.Author;
import ru.otus.hw08booksapp.entity.Book;
import ru.otus.hw08booksapp.entity.Genre;
import ru.otus.hw08booksapp.entity.Note;
import ru.otus.hw08booksapp.repository.AuthorRepository;
import ru.otus.hw08booksapp.repository.BookRepository;
import ru.otus.hw08booksapp.repository.NoteRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "vlad", runAlways = true)
    public void dropDb(AuthorRepository authorRepository, BookRepository bookRepository, NoteRepository noteRepository) {
        noteRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @ChangeSet(order = "002", id = "insertRecords", author = "vlad", runAlways = true)
    public void insertRecords(AuthorRepository authorRepository, BookRepository bookRepository, NoteRepository noteRepository) {
        var author1 = authorRepository.save(new Author("1", "Михаил Булгаков"));
        var author2 = authorRepository.save(new Author("2", "Антуан де Сент-Экзюпери"));
        var author3 = authorRepository.save(new Author("3", "Александр Дюма"));
        var author4 = authorRepository.save(new Author("4", "Аркадий и Борис Стругацкие"));

        var book1 = bookRepository.save(new Book("1", author1, new Genre("Роман"), "Мастер и Маргарита"));
        var book2 = bookRepository.save(new Book("2", author1, new Genre("Повесть"), "Собачье сердце"));
        var book3 = bookRepository.save(new Book("3", author2, new Genre("Сказка"), "Маленький принц"));
        var book4 = bookRepository.save(new Book("4", author2, new Genre("Сборник"), "Планета людей"));
        var book5 = bookRepository.save(new Book("5", author3, new Genre("Роман"), "Граф Монте-Кристо"));
        var book6 = bookRepository.save(new Book("6", author3, new Genre("Роман"), "Три мушкетёра"));
        var book7 = bookRepository.save(new Book("7", author4, new Genre("Фантастика"), "Трудно быть богом"));
        var book8 = bookRepository.save(new Book("8", author4, new Genre("Фантастика"), "Понедельник начинается в субботу"));

        var note1 = noteRepository.save(new Note("1", book1, "Первый отзыв для романа Мастер и Маргарита"));
        var note2 = noteRepository.save(new Note("2", book1, "Второй отзыв для романа Мастер и Маргарита"));
        var note3 = noteRepository.save(new Note("3", book1, "Отзыв для сказки Маленький принц"));
    }
}