package ru.otus.hw05booksapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private long id;

    private Author author;

    private Genre genre;

    private String title;

    public Book(String title, Author author, Genre genre) {
        this.author = author;
        this.genre = genre;
        this.title = title;
    }
}