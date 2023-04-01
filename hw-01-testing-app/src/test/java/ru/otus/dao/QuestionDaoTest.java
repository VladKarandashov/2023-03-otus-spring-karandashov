package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionDaoTest {

    @Test
    void getQuestionListTest() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenReturn(inputStream);

        QuestionDao questionDao = new QuestionDaoImpl(resource);
        questionDao.getQuestionList();
    }

    @Test
    void getQuestionListThrowTest() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException());
        QuestionDao questionDao = new QuestionDaoImpl(resource);

        Assertions.assertThrows(RuntimeException.class, questionDao::getQuestionList);
    }

}
