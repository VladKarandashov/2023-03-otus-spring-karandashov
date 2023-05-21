package ru.otus.hw07booksapp.entity;

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
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Note")
@NamedEntityGraph(
        name = "note-book",
        attributeNodes = @NamedAttributeNode(value = "book", subgraph = "book-author-genre"),
        subgraphs = {
                @NamedSubgraph(name = "book-author-genre",
                        attributeNodes = {
                            @NamedAttributeNode("author"),
                            @NamedAttributeNode("genre")}) })
@Table(name = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "id")
    private Book book;

    @NotBlank
    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", book=" + book +
                ", note='" + note + '\'' +
                '}';
    }
}