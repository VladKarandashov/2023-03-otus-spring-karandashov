package ru.otus.hw08booksapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    private String title;

    private String authorId;

    private String genreName;
}
