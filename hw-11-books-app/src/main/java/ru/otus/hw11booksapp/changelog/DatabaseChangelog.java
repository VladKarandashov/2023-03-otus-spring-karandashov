package ru.otus.hw11booksapp.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.BookNonReactiveRepository;

@Component
@ChangeLog
@NoArgsConstructor
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "vlad", runAlways = true)
    public void dropDb(BookNonReactiveRepository bookRepository) {
        bookRepository.deleteAll();
    }

    @ChangeSet(order = "002", id = "insertRecords", author = "vlad", runAlways = true)
    public void insertRecords(BookNonReactiveRepository bookRepository) {

        bookRepository.save(new Book("Мастер и Маргарита", new Author("Михаил Булгаков"), new Genre("Роман")));
        bookRepository.save(new Book("Собачье сердце", new Author("Михаил Булгаков"), new Genre("Повесть")));
        bookRepository.save(new Book("Маленький принц", new Author("Антуан де Сент-Экзюпери"), new Genre("Сказка")));
        bookRepository.save(new Book("Планета людей", new Author("Антуан де Сент-Экзюпери"), new Genre("Сборник")));
        bookRepository.save(new Book("Граф Монте-Кристо", new Author("Александр Дюма"), new Genre("Роман")));
        bookRepository.save(new Book("Три мушкетёра", new Author("Александр Дюма"), new Genre("Роман")));
        bookRepository.save(new Book("Трудно быть богом", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика")));
        bookRepository.save(new Book("Понедельник начинается в субботу", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика")));
    }
}