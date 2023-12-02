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
        log.info( "-> {} {} (studentID: {}) start the test", student.getFirstName(), student.getLastName(), student.getId());
        Thread.sleep(3000);
        log.info( "-> {} {} (studentID: {}) finished the test", student.getFirstName(), student.getLastName(), student.getId());
        return new Report(student.getId(), random.nextInt(1, 100));
    }
}