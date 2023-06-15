package ru.otus.hw09booksapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
public class BookCompleteDto {
    private Long id;

    @NotBlank
    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
