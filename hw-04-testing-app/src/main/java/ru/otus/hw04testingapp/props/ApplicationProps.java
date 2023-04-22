package ru.otus.hw04testingapp.props;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class ApplicationProps implements LocaleProvider, QuestionProvider {

    @Value("${questions.file}")
    private Resource file;

    @Value("${questions.needCountOfQuestionForSuccess}")
    private Integer needCountOfQuestionForSuccess;

    @Value("${application.locale}")
    private Locale locale;

}
