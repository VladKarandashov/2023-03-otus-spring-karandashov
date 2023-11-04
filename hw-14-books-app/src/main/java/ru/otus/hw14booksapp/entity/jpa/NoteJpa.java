package ru.otus.hw14booksapp.entity.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.hw14booksapp.entity.Note;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Note")
@Table(name = "Note")
public class NoteJpa implements Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = BookJpa.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
    private BookJpa book;

    @NotBlank
    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}