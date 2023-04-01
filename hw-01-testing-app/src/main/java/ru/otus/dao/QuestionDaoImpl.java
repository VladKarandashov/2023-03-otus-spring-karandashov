package ru.otus.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final Resource resource;

    @Autowired
    public QuestionDaoImpl(@Value("classpath:${questions.filename}") Resource file) {
        this.resource = file;
    }

    @Override
    public List<Question> getQuestionList() {
        //System.out.println("question file received: " + nameFile);
        try (InputStream inputStream = resource.getInputStream()) {
            return readLinesItems(inputStream).stream()
                    .skip(1)
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
        } catch (Exception e) {
            throw new RuntimeException("Missing list of questions", e);
        }

    }

    private List<List<String>> readLinesItems(InputStream inputStream) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Wrong file structure");
        }
        return records;
    }
}
