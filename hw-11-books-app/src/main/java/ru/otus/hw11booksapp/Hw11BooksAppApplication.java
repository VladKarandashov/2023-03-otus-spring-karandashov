package ru.otus.hw11booksapp;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Hw11BooksAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw11BooksAppApplication.class, args);
    }

}
