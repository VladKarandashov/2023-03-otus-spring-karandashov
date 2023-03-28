package ru.otus.service;

import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Scanner;

public class TestingServiceImpl implements TestingService {

    private int successCount;

    private final QuestionDao questionDao;

    public TestingServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void testing() {
        List<Question> questionList = questionDao.getQuestionList();
        Scanner in = new Scanner(System.in);
        successCount = 0;

        System.out.println("Hello, welcome to the testing program!");
        System.out.println("You have to answer "+questionList.size()+" questions");

        for (Question question : questionList) {
            System.out.println("\nQuestion №"+question.getId()+" - "+question.getTitle());
            System.out.println(question.getText());
            System.out.println("Answer options (select one):");
            question.getAnswersValue().forEach(el -> System.out.println("\t"+el));
            System.out.print("Enter your answer: ");
            String answer = in.next();
            System.out.println(check(answer, question));
        }

        System.out.println("---------------------------------------------------");
        System.out.println("you answered "+successCount+" questions out of "+questionList.size()+" correctly");
    }

    private boolean check(String answer, Question question) {
        boolean verdict = question.checkOption(answer);
        if (verdict) {
            successCount += 1;
        }
        return verdict;
    }
}
