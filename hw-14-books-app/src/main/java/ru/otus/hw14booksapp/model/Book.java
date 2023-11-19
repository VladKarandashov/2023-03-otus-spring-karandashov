package ru.otus.hw14booksapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private String id;

	private String name;

	private Genre genre;

	private Author author;

}
