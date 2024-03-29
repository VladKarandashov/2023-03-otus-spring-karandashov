package ru.otus.hw11booksapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;

    @NotBlank
    private String title;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{0,256}$")
    private String author;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{0,256}$")
    private String genre;
}
