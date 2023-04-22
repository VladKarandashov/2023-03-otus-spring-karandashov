package ru.otus.hw04testingapp.dao;

import ru.otus.hw04testingapp.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
