package ru.otus.hw18booksapp.actuator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.hw18booksapp.dao.repository.BookRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookCountHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {

        try {
            long booksCount = bookRepository.count();
            return booksCount == 0 ?
                    Health.down().status(Status.DOWN).withDetail("message", "Количество книг равно нулю!").build() :
                    Health.up().status(Status.UP).withDetail("message", "Количество книг больше 0").build();
        } catch (Exception e) {
            log.error("Произошла ошибка при попытке подсчитать количество книг в БД", e);
            return Health.down().status(Status.DOWN)
                    .withDetail("message", "Количество книг равно нулю!")
                    .withException(e)
                    .build();
        }
    }
}