package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class QuestionDaoImplTest {

    @Test
    void getQuestionListTest() throws IOException {
        InputStream inputStream = Mockito.mock(InputStream.class);
        Resource resource = Mockito.mock(Resource.class);
        Mockito.when(resource.getInputStream()).thenReturn(inputStream);

        QuestionDao questionDao = new QuestionDaoImpl(resource);
        questionDao.getQuestionList();
    }

    @Test
    void getQuestionListThrowTest() throws IOException {
        Resource resource = Mockito.mock(Resource.class);
        Mockito.when(resource.getInputStream()).thenReturn(null);

        QuestionDao questionDao = new QuestionDaoImpl(resource);
        Assertions.assertThrows(RuntimeException.class, questionDao::getQuestionList);
    }
}
