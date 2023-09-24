package ru.otus.hw08booksapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Hw08BooksAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw08BooksAppApplication.class, args);
    }

}
