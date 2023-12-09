package ru.otus.hw17booksapp.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.hw17booksapp.service.BookService;

@Component
@RequiredArgsConstructor
public class BookListHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        boolean isServerDown = bookService.getAll().isEmpty();
        if (isServerDown) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Количество книг равно нулю!")
                    .build();
        } else {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "Количество книг больше 0")
                    .build();
        }
    }
}