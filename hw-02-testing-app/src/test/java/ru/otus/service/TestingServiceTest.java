package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoImpl;
import ru.otus.service.impl.IOServiceStreams;
import ru.otus.service.impl.TestingServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class TestingServiceTest {

    @Test
    void testingTest() {
        QuestionDao questionDao = Mockito.mock(QuestionDaoImpl.class);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        InputStream inputStream = Mockito.mock(InputStream.class);
        IOService ioService = new IOServiceStreams(printStream, inputStream);

        TestingService testingService = new TestingServiceImpl(3, questionDao, ioService);
        testingService.testing();

        String resultOutput = outputStream.toString();
        List<String> lines = resultOutput.lines().toList();
        System.out.println(lines);
        Assertions.assertTrue(lines.contains("Result"));
    }
}
