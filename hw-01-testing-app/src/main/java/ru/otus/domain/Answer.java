package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Answer {
    private final String value;

    private final boolean isCorrect;
}
