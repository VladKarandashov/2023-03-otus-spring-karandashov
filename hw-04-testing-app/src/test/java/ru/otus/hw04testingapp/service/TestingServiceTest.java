package ru.otus.hw04testingapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import ru.otus.hw04testingapp.dao.QuestionDao;
import ru.otus.hw04testingapp.dao.QuestionDaoImpl;
import ru.otus.hw04testingapp.props.ApplicationProps;
import ru.otus.hw04testingapp.props.QuestionProvider;
import ru.otus.hw04testingapp.service.impl.IOServiceStreams;
import ru.otus.hw04testingapp.service.impl.LocalizedServiceImpl;
import ru.otus.hw04testingapp.service.impl.TestingServiceImpl;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class TestingServiceTest {

    @Test
    void testingTest() throws IOException {
        // Выполняем приготовления (заготавливаем mock-и)
        var resource = prepareResource();
        QuestionProvider questionsProps = prepareQuestionProps(resource);
        ApplicationProps applicationProps = prepareApplicationProps();
        MessageSource messageSource = prepareMessageSource();
        LocalizedService localizedService = prepareLocalizedService(messageSource, applicationProps);

        // Заготавливаем подменные потоки ввода-вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        InputStream inputStream = new ByteArrayInputStream("ANSWER".getBytes());
        IOService ioService = new IOServiceStreams(printStream, inputStream);

        // Тестируем
        QuestionDao questionDao = new QuestionDaoImpl(questionsProps);
        TestingService testingService = new TestingServiceImpl(localizedService, questionsProps, questionDao, ioService);
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

    private static Resource prepareResource() throws IOException {
        Resource resource = Mockito.mock(Resource.class);
        InputStream inputStreamIn = new ByteArrayInputStream(
                ("id,title,question,answer,option1,option2,option3\n" +
                        "1,HEAD,BODY,ANSWER,WRONG_ANSWER,ANSWER,NOT_ANSWER").getBytes()
        );
        Mockito.when(resource.getInputStream()).thenReturn(inputStreamIn);
        return resource;
    }

    private static MessageSource prepareMessageSource() {
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        Mockito.when(messageSource.getMessage("HEAD", null, Locale.ENGLISH)).thenReturn("HEAD");
        Mockito.when(messageSource.getMessage("BODY", null, Locale.ENGLISH)).thenReturn("BODY");
        return messageSource;
    }

    private static ApplicationProps prepareApplicationProps() {
        ApplicationProps applicationProps = Mockito.mock(ApplicationProps.class);
        Mockito.when(applicationProps.getLocale()).thenReturn(Locale.ENGLISH);
        return applicationProps;
    }

    private static QuestionProvider prepareQuestionProps(Resource resource) {
        QuestionProvider questionProvider = Mockito.mock(QuestionProvider.class);
        Mockito.when(questionProvider.getFile()).thenReturn(resource);
        return questionProvider;
    }

    private static LocalizedService prepareLocalizedService(MessageSource messageSource, ApplicationProps props) {
        return new LocalizedServiceImpl(messageSource, props);
    }
}
