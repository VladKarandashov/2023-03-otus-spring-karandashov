package ru.otus.hw07booksapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    private String title;

    private long authorId;

    private long genreId;
}
