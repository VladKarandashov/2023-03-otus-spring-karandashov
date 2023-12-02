package ru.otus.hw15integrationapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw15integrationapp.util.Util;

@Data
@AllArgsConstructor
public class Student {

    private Long id;
    private String firstName;
    private String lastName;

    public Student(Long id) {
        this.id = id;
        this.firstName = Util.faker().name().firstName();
        this.lastName = Util.faker().name().lastName();
    }
}