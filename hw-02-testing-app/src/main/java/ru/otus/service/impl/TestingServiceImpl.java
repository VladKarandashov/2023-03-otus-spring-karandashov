package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.TestingService;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private int successCount;

    private final int needSuccessCount;

    private final QuestionDao questionDao;

    private final IOService ioService;

    @Autowired
    public TestingServiceImpl(@Value("${questions.needCountOfQuestionForSuccess}") int needSuccessCount,
                              QuestionDao questionDao,
                              IOService ioService) {
        this.needSuccessCount = needSuccessCount;
        this.questionDao = questionDao;
        this.ioService = ioService;
    }

    @Override
    public void testing() {
        List<Question> questionList = questionDao.getQuestionList();
        successCount = 0;

        greeting(questionList);
        testingQuestions(questionList);
        printResult(questionList);
    }

    private void greeting(List<Question> questionList) {
        ioService.printString("Hello, welcome to the testing program!");
        ioService.printString("You have to answer " + questionList.size() + " questions");
        ioService.printString("Need to answer " + needSuccessCount + " questions correctly");
    }

    private void testingQuestions(List<Question> questionList) {
        questionList.forEach(this::testingQuestion);
    }

    private void testingQuestion(Question question) {
        ioService.printString("\nQuestion №" + question.getId() + " - " + question.getTitle());
        ioService.printString(question.getText());
        ioService.printString("Answer options (select one):");
        question.getAnswersValue().forEach(el -> ioService.printString("\t" + el));
        String answer = ioService.readStringWithPrompt("Enter your answer: ");
        ioService.printString(String.valueOf(check(answer, question)));
    }

    private boolean check(String answer, Question question) {
        boolean verdict = question.checkOption(answer);
        if (verdict) {
            successCount += 1;
        }
        return verdict;
    }

    private void printResult(List<Question> questionList) {
        ioService.printString("---------------------------------------------------");
        ioService.printString("you answered "+successCount+" questions out of "+questionList.size()+" correctly");
        ioService.printString("you had to correctly answer "+needSuccessCount+" questions");
        ioService.printString(successCount<needSuccessCount ? "You didn't pass the test" : "you passed the test");
    }
}
