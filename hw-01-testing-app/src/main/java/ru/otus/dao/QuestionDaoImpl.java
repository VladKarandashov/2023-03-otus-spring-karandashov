package ru.otus.dao;

import lombok.Getter;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.utils.CsvParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionDaoImpl implements QuestionDao {

    private static final String SEPARATOR = ";";

    private final CsvParser csvParser;

    private final String nameFile;

    public QuestionDaoImpl(CsvParser csvParser, String nameFile) {
        this.csvParser = csvParser;
        this.nameFile = nameFile;
    }

    public List<Question> getQuestionList() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        return csvParser.readLinesItems(inputStream, SEPARATOR, true).stream()
                .map(item -> {
                    Long id = Long.valueOf(item.get(0));
                    String title = item.get(1);
                    String text = item.get(2);
                    String correctAnswer = item.get(3);
                    List<Answer> answers = new ArrayList<>();
                    for (int i = 4; i < item.size(); i++) {
                        String value = item.get(i);
                        var answer = new Answer(value, correctAnswer.equalsIgnoreCase(value));
                        answers.add(answer);
                    }
                    return new Question(id, title, text, answers);
                }
        ).collect(Collectors.toList());
    }
}
