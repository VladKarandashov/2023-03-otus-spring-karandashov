package ru.otus.hw09booksapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDto {
    private Long id;

    private String note;
}
