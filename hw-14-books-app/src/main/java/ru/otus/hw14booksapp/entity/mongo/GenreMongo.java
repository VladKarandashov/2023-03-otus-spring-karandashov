package ru.otus.hw14booksapp.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.hw14booksapp.entity.Genre;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Genre")
public class GenreMongo implements Genre {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}