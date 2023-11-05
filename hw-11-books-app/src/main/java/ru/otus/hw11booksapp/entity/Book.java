package ru.otus.hw11booksapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("book")
public class Book {

    @Id
    @Column("id")
    private Long id;

    @Column("author_id")
    private Long authorId;

    @Column("genre_id")
    private Long genreId;

    @Column("title")
    private String title;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}