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
@Table(name = "AUTHORS")
public class AuthorJpa {

	@Id
	@Column(name = "AUTHOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "PATRONYMIC")
	private String patronymic;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", patronymic='" + patronymic + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}
}
