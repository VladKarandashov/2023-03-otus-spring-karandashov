package ru.otus.hw11booksapp.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String name;

    @Override
    public String toString() {
        return "Author{" +
                ", name='" + name + '\'' +
                '}';
    }
}