package ru.otus.hw14booksapp.entity.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.hw14booksapp.entity.Book;

@Getter
@Setter
@Entity(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "book-author-genre",
        attributeNodes = {@NamedAttributeNode("author"),
                          @NamedAttributeNode("genre")})
@Table(name = "book")
public class BookJpa implements Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, targetEntity = AuthorJpa.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    private AuthorJpa author;

    @ManyToOne(optional = false, targetEntity = GenreJpa.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "id")
    private GenreJpa genre;

    @Column(name = "title")
    private String title;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}