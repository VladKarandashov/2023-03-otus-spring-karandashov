package ru.otus.hw04testingapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import ru.otus.hw04testingapp.exception.MissingQuestionsException;
import ru.otus.hw04testingapp.props.QuestionProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class QuestionDaoImplTest {

    @Test
    void getAllTest() throws IOException {
        InputStream inputStreamIn = new ByteArrayInputStream(
                ("id,title,question,answer,option1,option2,option3\n" +
                        "1,HEAD,BODY,ANSWER,WRONG_ANSWER,ANSWER,NOT_ANSWER").getBytes()
        );
        Resource resource = Mockito.mock(Resource.class);
        Mockito.when(resource.getInputStream()).thenReturn(inputStreamIn);
        QuestionProvider questionsProps = Mockito.mock(QuestionProvider.class);
        Mockito.when(questionsProps.getFile()).thenReturn(resource);

        QuestionDao questionDao = new QuestionDaoImpl(questionsProps);
        var result = questionDao.getAll();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getAllThrowMissingQuestionsExceptionTest() throws IOException {
        Resource resource = Mockito.mock(Resource.class);
        Mockito.when(resource.getInputStream()).thenReturn(null);
        QuestionProvider questionsProps = Mockito.mock(QuestionProvider.class);
        Mockito.when(questionsProps.getFile()).thenReturn(resource);

        QuestionDao questionDao = new QuestionDaoImpl(questionsProps);
        Assertions.assertThrows(MissingQuestionsException.class, questionDao::getAll);
    }
}
