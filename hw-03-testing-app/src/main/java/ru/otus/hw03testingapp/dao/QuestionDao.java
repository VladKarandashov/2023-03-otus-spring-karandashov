package ru.otus.hw03testingapp.dao;


import ru.otus.hw03testingapp.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
