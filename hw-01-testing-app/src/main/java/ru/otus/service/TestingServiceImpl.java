package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Scanner;

@Service
public class TestingServiceImpl implements TestingService {

    private int successCount;

    private final QuestionDao questionDao;

    private final int requiredQuestionsCount;

    @Autowired
    public TestingServiceImpl(QuestionDao questionDao, @Value("${questions.required}") Integer requiredQuestionsCount) {
        this.questionDao = questionDao;
        this.requiredQuestionsCount = requiredQuestionsCount;
    }

    @Override
    public void testing() {
        Scanner in = new Scanner(System.in);

        List<Question> questionList = questionDao.getQuestionList();
        successCount = 0;

        System.out.println("Hello, welcome to the testing program!");
        System.out.println("You have to answer " + questionList.size() + " questions");

        System.out.print("Enter your name: ");
        String userName = in.next();
        System.out.println("Answer the questions please\n");

        testing(questionList);

        System.out.println("---------------------------------------------------");
        System.out.println(
                "Dear " + userName +
                ", you correctly answered " + successCount +
                " questions out of " + questionList.size()
        );

        System.out.println("Number of correct answers required to score: " + requiredQuestionsCount);
        System.out.println("Result of test: " + (successCount >= requiredQuestionsCount?"Success":"Failure"));
    }

    private void testing(List<Question> questionList) {
        Scanner in = new Scanner(System.in);
        for (Question question : questionList) {
            System.out.println("\nQuestion №" + question.getId() + " - " + question.getTitle());
            System.out.println(question.getText());
            System.out.println("Answer options (select one):");
            question.getAnswersValue().forEach(el -> System.out.println("\t" + el));
            System.out.print("Enter your answer: ");
            String answer = in.next();
            System.out.println(check(answer, question));
        }
    }

    private boolean check(String answer, Question question) {
        boolean verdict = question.checkOption(answer);
        if (verdict) {
            successCount += 1;
        }
        return verdict;
    }
}
