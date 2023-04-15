package ru.otus.hw03testingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw03testingapp.dao.QuestionDao;
import ru.otus.hw03testingapp.domain.Question;
import ru.otus.hw03testingapp.props.QuestionProvider;
import ru.otus.hw03testingapp.service.IOService;
import ru.otus.hw03testingapp.service.LocalizedService;
import ru.otus.hw03testingapp.service.TestingService;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private int successCount;

    private final int needSuccessCount;

    private final QuestionDao questionDao;

    private final IOService ioService;

    private final LocalizedService localizedService;

    @Autowired
    public TestingServiceImpl(LocalizedService localizedService,
                              QuestionProvider questionsProps,
                              QuestionDao questionDao,
                              IOService ioService) {
        this.needSuccessCount = questionsProps.getNeedCountOfQuestionForSuccess();
        this.questionDao = questionDao;
        this.ioService = ioService;
        this.localizedService = localizedService;
    }

    @Override
    public void testing() {
        List<Question> questionList = questionDao.getAll();
        successCount = 0;

        greeting(questionList);
        testingQuestions(questionList);
        printResult(questionList);
    }

    private void greeting(List<Question> questionList) {
        ioService.printLn(localizedService.getLocalizedString("greetings"));
        ioService.printLn(localizedService.getLocalizedString("countQuestions", new Integer[]{questionList.size()}));
        ioService.printLn(localizedService.getLocalizedString("needAnswer", new Integer[]{needSuccessCount}));
    }

    private void testingQuestions(List<Question> questionList) {
        questionList.forEach(this::testingQuestion);
    }

    private void testingQuestion(Question question) {
        ioService.printLn("\n№"+question.getId());
        ioService.printLn(localizedService.getLocalizedString(question.getTitle()));
        ioService.printLn(localizedService.getLocalizedString(question.getText()));
        ioService.printLn(localizedService.getLocalizedString("answer"));
        question.getAnswersValue().forEach(ioService::printLn);
        ioService.printLn(localizedService.getLocalizedString("enterAnswer"));
        String answer = ioService.readLn();
        ioService.printLn(localizedService.getLocalizedString(String.valueOf(check(answer, question))));
    }

    private boolean check(String answer, Question question) {
        boolean verdict = question.checkOption(answer);
        if (verdict) {
            successCount += 1;
        }
        return verdict;
    }

    private void printResult(List<Question> questionList) {
        ioService.printLn(localizedService.getLocalizedString("result"));
        ioService.printLn(
                localizedService.getLocalizedString("statistic", new Integer[]{successCount, questionList.size()})
        );
        if (successCount < needSuccessCount) {
            ioService.printLn(localizedService.getLocalizedString("notPass"));
        } else {
            ioService.printLn(localizedService.getLocalizedString("pass"));
        }
    }
}
