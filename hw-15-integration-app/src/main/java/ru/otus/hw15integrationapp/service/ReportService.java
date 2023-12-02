package ru.otus.hw15integrationapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;

import java.util.Random;

@Slf4j
@Service
public class ReportService {

    private static final Random random = new Random();

    public Report generateReport(Student student) throws InterruptedException {
        log.info( "-> -> начал тест {}", student);
        Thread.sleep(3000);
        log.info( "-> -> закончил тест {}", student);
        return new Report(student, random.nextInt(1, 100));
    }
}