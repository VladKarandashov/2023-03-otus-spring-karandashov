package ru.otus.hw14booksapp.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.hw14booksapp.entity.Author;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Author")
public class AuthorMongo implements Author {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}