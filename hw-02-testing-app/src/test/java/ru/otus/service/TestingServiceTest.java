package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoImpl;
import ru.otus.service.impl.IOServiceStreams;
import ru.otus.service.impl.TestingServiceImpl;

import java.io.*;
import java.util.List;

public class TestingServiceTest {

    @Test
    void testingTest() throws IOException {
        Resource resource = Mockito.mock(Resource.class);
        InputStream inputStreamIn = new ByteArrayInputStream(
                ("id,title,question,answer,option1,option2,option3\n" +
                        "1,HEAD,BODY,ANSWER,WRONG_ANSWER,ANSWER,NOT_ANSWER").getBytes()
        );
        Mockito.when(resource.getInputStream()).thenReturn(inputStreamIn);

        QuestionDao questionDao = new QuestionDaoImpl(resource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        InputStream inputStream = new ByteArrayInputStream("ANSWER".getBytes());
        IOService ioService = new IOServiceStreams(printStream, inputStream);

        TestingService testingService = new TestingServiceImpl(3, questionDao, ioService);
        testingService.testing();

        String resultOutput = outputStream.toString();
        List<String> lines = resultOutput.lines().toList();
        System.out.println(lines);

        Assertions.assertTrue(lines.contains("You have to answer 1 questions"));
        Assertions.assertTrue(lines.contains("true"));
        Assertions.assertTrue(lines.contains("Result"));
        Assertions.assertTrue(lines.contains("you answered 1 questions out of 1 correctly"));
    }
}
