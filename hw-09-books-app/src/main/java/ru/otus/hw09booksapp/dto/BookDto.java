package ru.otus.hw09booksapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    Long id;
    String title;
    String author;
    String genre;
}
