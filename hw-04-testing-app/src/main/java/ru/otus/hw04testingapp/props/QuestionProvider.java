package ru.otus.hw04testingapp.props;

import org.springframework.core.io.Resource;

public interface QuestionProvider {
    Resource getFile();

    Integer getNeedCountOfQuestionForSuccess();
}
