package ru.otus.hw14booksapp.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.hw14booksapp.entity.Note;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Note")
public class NoteMongo implements Note {

    @Id
    private String id;

    @DBRef
    private BookMongo book;

    @Field(name = "note")
    private String note;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}