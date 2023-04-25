package ru.otus.hw04testingapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.hw04testingapp.service.impl.IOServiceStreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

@SpringBootTest
public class TestingServiceTest {

    static InputStream inputStream;
    static ByteArrayOutputStream outputStream;

    @Autowired
    TestingService testingService;

    @Test
    void testingTest() {
        // Тестируем
        testingService.testing();

        // Получаем из output результат вывода
        List<String> lines = outputStream.toString().lines().toList();

        // Проверяем вывод на правильность
        Assertions.assertTrue(lines.contains("№1"));
        Assertions.assertTrue(lines.contains("HEAD"));
        Assertions.assertTrue(lines.contains("BODY"));
        Assertions.assertTrue(lines.contains("WRONG_ANSWER"));
        Assertions.assertTrue(lines.contains("ANSWER"));
        Assertions.assertTrue(lines.contains("NOT_ANSWER"));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        IOServiceStreams IOServiceStreams() {
            // Заготавливаем подменные потоки ввода-вывода
            outputStream = new ByteArrayOutputStream();
            var printStream = new PrintStream(outputStream);
            inputStream = new ByteArrayInputStream("ANSWER".getBytes());

            // Переопределяем IOService
            return new IOServiceStreams(printStream, inputStream);
        }
    }
}
