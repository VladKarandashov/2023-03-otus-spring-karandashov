package ru.otus.hw06booksapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "book-author-genre",
        attributeNodes = {@NamedAttributeNode("author"),
                          @NamedAttributeNode("genre")})
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(optional = false, targetEntity = Author.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size = 5)
    private Author author;

    @ManyToOne(optional = false, targetEntity = Genre.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "id")
    private Genre genre;

    @Column(name = "title")
    private String title;


}