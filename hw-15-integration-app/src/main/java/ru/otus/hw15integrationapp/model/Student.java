package ru.otus.hw15integrationapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private Long groupNumber;

    private Long number;

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", number=" + number +
                '}';
    }
}