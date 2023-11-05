package ru.otus.hw13booksapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCompleteDto {
    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
