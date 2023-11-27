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

        var book1 = bookRepository.save(new Book("Мастер и Маргарита", new Author("Михаил Булгаков"), new Genre("Роман"))).subscribe();
        var book2 = bookRepository.save(new Book("Собачье сердце", new Author("Михаил Булгаков"), new Genre("Повесть"))).subscribe();
        var book3 = bookRepository.save(new Book("Маленький принц", new Author("Антуан де Сент-Экзюпери"), new Genre("Сказка"))).subscribe();
        var book4 = bookRepository.save(new Book("Планета людей", new Author("Антуан де Сент-Экзюпери"), new Genre("Сборник"))).subscribe();
        var book5 = bookRepository.save(new Book("Граф Монте-Кристо", new Author("Александр Дюма"), new Genre("Роман"))).subscribe();
        var book6 = bookRepository.save(new Book("Три мушкетёра", new Author("Александр Дюма"), new Genre("Роман"))).subscribe();
        var book7 = bookRepository.save(new Book("Трудно быть богом", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика"))).subscribe();
        var book8 = bookRepository.save(new Book("Понедельник начинается в субботу", new Author("Аркадий и Борис Стругацкие"), new Genre("Фантастика"))).subscribe();
    }
}