package ru.otus.hw11booksapp.changelog;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw11booksapp.entity.Author;
import ru.otus.hw11booksapp.entity.Book;
import ru.otus.hw11booksapp.entity.Genre;
import ru.otus.hw11booksapp.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class DatabaseChangelog {

    private final BookRepository bookRepository;

    @PostConstruct
    public void init() {
        dropDb();
        insertRecords();
    }

    public void dropDb() {
        bookRepository.deleteAll().subscribe();
    }

    public void insertRecords() {

        var book1 = bookRepository.save(new Book("1", new Author("Михаил Булгаков"), new Genre("Роман"), "Мастер и Маргарита")).subscribe();
        var book2 = bookRepository.save(new Book("2", new Author("Михаил Булгаков"), new Genre("Повесть"), "Собачье сердце")).subscribe();
        var book3 = bookRepository.save(new Book("3", new Author("Антуан де Сент-Экзюпери"), new Genre("Сказка"), "Маленький принц")).subscribe();
        var book4 = bookRepository.save(new Book("4", new Author("Антуан де Сент-Экзюпери"), new Genre("Сборник"), "Планета людей")).subscribe();
        var book5 = bookRepository.save(new Book("5", new Author("Александр Дюма"), new Genre("Роман"), "Граф Монте-Кристо")).subscribe();
        var book6 = bookRepository.save(new Book("6", new Author("Александр Дюма"), new Genre("Роман"), "Три мушкетёра")).subscribe();
        var book7 = bookRepository.save(new Book("7", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика"), "Трудно быть богом")).subscribe();
        var book8 = bookRepository.save(new Book("8", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика"), "Понедельник начинается в субботу")).subscribe();
    }
}