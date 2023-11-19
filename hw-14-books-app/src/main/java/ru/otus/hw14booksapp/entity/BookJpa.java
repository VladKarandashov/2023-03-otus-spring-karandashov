package ru.otus.hw14booksapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(
	name = "bookWithGenreAndAuthor",
	attributeNodes = {
		@NamedAttributeNode("genre"),
		@NamedAttributeNode("author")
	}
)
public class BookJpa {

	@Id
	@Column(name = "BOOK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "GENRE_ID")
	private GenreJpa genre;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
	private AuthorJpa author;

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
