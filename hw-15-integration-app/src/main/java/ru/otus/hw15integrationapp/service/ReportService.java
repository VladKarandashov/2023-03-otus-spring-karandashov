package ru.otus.hw15integrationapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.hw15integrationapp.config.ExamProperties;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ExamProperties examProperties;
    private static final Random random = new Random();

    public Report generateReport(Student student) {
        log.info( "-> начал тест студент {}", student.getId());

        // оценка за экзамен прямо пропорциональна потраченному времени!
        var testResult = random.nextLong(1, 100);
        sleep(testResult);

        log.info( "<- закончил тест студент {}", student.getId());
        return new Report(student.getId(), testResult);
    }

    private void sleep(long testResult) {
        try {
            Thread.sleep(examProperties.getMinimalDuration() + testResult * 100);
        } catch (Exception ignored) {}
    }
}