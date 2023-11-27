package ru.otus.hw11booksapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "Book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @Field(name = "author")
    private Author author;

    @Field(name = "genre")
    private Genre genre;

    @Field(name = "title")
    private String title;

    public Book(Author author, Genre genre, String title) {
        this.author = author;
        this.genre = genre;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}