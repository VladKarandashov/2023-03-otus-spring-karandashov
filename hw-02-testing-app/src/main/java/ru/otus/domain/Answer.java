package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Answer {
    private final String value;

    private final boolean isCorrect;
}
