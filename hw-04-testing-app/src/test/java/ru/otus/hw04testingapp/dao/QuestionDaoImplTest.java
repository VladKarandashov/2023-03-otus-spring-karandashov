package ru.otus.hw04testingapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionDaoImplTest {

    @Autowired
    QuestionDao questionDao;

    @Test
    void getAllTest() {
        var result = questionDao.getAll();
        Assertions.assertEquals(1, result.size());
    }
}
