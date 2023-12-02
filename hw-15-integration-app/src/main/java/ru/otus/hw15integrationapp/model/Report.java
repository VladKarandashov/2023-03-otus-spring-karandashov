package ru.otus.hw15integrationapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Report {

    private Student student;
    private int testResult;
}