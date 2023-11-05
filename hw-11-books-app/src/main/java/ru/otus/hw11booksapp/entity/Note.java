package ru.otus.hw11booksapp.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("note")
public class Note {

    @Id
    @Column("id")
    private Long id;

    @Column("book_id")
    private Long bookId;

    @NotBlank
    @Column("note")
    private String note;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}