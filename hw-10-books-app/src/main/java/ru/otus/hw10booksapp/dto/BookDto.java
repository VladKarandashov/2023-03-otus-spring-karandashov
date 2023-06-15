package ru.otus.hw10booksapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;

    @NotBlank
    private String title;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{0,256}$")
    private String author;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{0,256}$")
    private String genre;
}
