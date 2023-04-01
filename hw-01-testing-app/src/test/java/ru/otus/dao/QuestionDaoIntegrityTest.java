package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Question;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class, QuestionDaoImpl.class})
public class QuestionDaoIntegrityTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void getQuestionListTest() {
        List<Question> list = questionDao.getQuestionList();
        Assertions.assertTrue(list.size()>0);
    }
}
