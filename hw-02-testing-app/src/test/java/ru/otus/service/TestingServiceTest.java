package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoImpl;
import ru.otus.service.impl.IOServiceStreams;
import ru.otus.service.impl.TestingServiceImpl;

public class TestingServiceTest {

    @Test
    void testingTest() {
        QuestionDao questionDao = Mockito.mock(QuestionDaoImpl.class);
        IOService ioService = Mockito.mock(IOServiceStreams.class);

        TestingService testingService = new TestingServiceImpl(3, questionDao, ioService);
        testingService.testing();
    }
}
