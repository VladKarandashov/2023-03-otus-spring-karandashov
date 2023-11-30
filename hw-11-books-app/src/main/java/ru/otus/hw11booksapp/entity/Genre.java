package ru.otus.hw11booksapp.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    private String name;

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}