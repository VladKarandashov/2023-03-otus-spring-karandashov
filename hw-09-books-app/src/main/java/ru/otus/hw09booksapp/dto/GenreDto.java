package ru.otus.hw09booksapp.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private Long id;

    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]{0,256}$")
    private String name;
}
