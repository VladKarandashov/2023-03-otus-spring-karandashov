package ru.otus.hw07booksapp.view;

import java.util.List;

public interface EntityFormatter<T> {
    String format(T entity);

    default String format(List<T> entities) {
        StringBuilder result = new StringBuilder();
        entities.forEach(entity ->
                result.append(format(entity)).append("\n")
        );
        return result.toString();
    }
}
