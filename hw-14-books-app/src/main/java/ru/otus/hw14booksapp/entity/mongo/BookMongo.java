package ru.otus.hw14booksapp.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.hw14booksapp.entity.Book;

@Getter
@Setter
@Document(collection = "Book")
@AllArgsConstructor
@NoArgsConstructor
public class BookMongo implements Book {

    @Id
    private String id;

    @DBRef
    private AuthorMongo author;

    @Field(name = "genre")
    private GenreMongo genre;

    @Field(name = "title")
    private String title;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}