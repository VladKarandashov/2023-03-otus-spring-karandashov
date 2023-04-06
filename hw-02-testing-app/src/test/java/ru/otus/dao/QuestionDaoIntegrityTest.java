package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.config.ApplicationTestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class, QuestionDaoImpl.class})
public class QuestionDaoIntegrityTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    public void integrityDaoTest() {
        var result = questionDao.getQuestionList();
        Assertions.assertTrue(result.size() > 0);
        var question = result.get(0);
        Assertions.assertTrue(question.checkOption("ANSWER"));
    }

}
