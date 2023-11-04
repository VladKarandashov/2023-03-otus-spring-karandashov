package ru.otus.hw14booksapp.entity.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.hw14booksapp.entity.Genre;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Genre")
@Table(name = "genre")
public class GenreJpa implements Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}