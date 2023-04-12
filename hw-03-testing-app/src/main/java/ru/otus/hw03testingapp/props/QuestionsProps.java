package ru.otus.hw03testingapp.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Data
@ConfigurationProperties(prefix = "questions")
public class QuestionsProps {

    private final Resource file;

    private final Integer needCountOfQuestionForSuccess;
}
