package ru.otus.hw03testingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw03testingapp.dao.QuestionDao;
import ru.otus.hw03testingapp.domain.Question;
import ru.otus.hw03testingapp.props.QuestionsProps;
import ru.otus.hw03testingapp.service.IOService;
import ru.otus.hw03testingapp.service.TestingService;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private int successCount;

    private final int needSuccessCount;

    private final QuestionDao questionDao;

    private final IOService ioService;

    @Autowired
    public TestingServiceImpl(QuestionsProps questionsProps,
                              QuestionDao questionDao,
                              IOService ioService) {
        this.needSuccessCount = questionsProps.getNeedCountOfQuestionForSuccess();
        this.questionDao = questionDao;
        this.ioService = ioService;
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
        ioService.printStringByLocale("greetings");
        ioService.printStringByLocale("countQuestions", new Integer[]{questionList.size()});
        ioService.printStringByLocale("needAnswer", new Integer[]{needSuccessCount});
    }

    private void testingQuestions(List<Question> questionList) {
        questionList.forEach(this::testingQuestion);
    }

    private void testingQuestion(Question question) {
        ioService.printString("\n№"+question.getId());
        ioService.printStringByLocale(question.getTitle());
        ioService.printStringByLocale(question.getText());
        ioService.printStringByLocale("answer");
        question.getAnswersValue().forEach(ioService::printString);
        ioService.printStringByLocale("enterAnswer");
        String answer = ioService.readString();
        ioService.printStringByLocale(String.valueOf(check(answer, question)));
    }

    private boolean check(String answer, Question question) {
        boolean verdict = question.checkOption(answer);
        if (verdict) {
            successCount += 1;
        }
        return verdict;
    }

    private void printResult(List<Question> questionList) {
        ioService.printStringByLocale("result");
        ioService.printStringByLocale("statistic", new Integer[]{successCount, questionList.size()});
        if (successCount < needSuccessCount) {
            ioService.printStringByLocale("notPass");
        } else {
            ioService.printStringByLocale("pass");
        }
    }
}
