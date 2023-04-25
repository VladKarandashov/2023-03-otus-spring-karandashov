package ru.otus.hw04testingapp.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.hw04testingapp.domain.Answer;
import ru.otus.hw04testingapp.domain.Question;
import ru.otus.hw04testingapp.exception.InvalidFileStructureException;
import ru.otus.hw04testingapp.exception.MissingQuestionsException;
import ru.otus.hw04testingapp.props.QuestionProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final Resource questionFile;

    public QuestionDaoImpl(QuestionProvider questionProvider) {
        this.questionFile = questionProvider.getFile();
    }

    @Override
    public List<Question> getAll() {
        try (InputStream inputStream = questionFile.getInputStream()) {
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
            throw new MissingQuestionsException("Missing list of questions", e);
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
            throw new InvalidFileStructureException("Wrong file structure", e);
        }
        return records;
    }
}
