package ru.otus.hw15integrationapp.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;


import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegrationService {

    private static final Random random = new Random();

    private final ReportGateway reportGateway;

    @Async
    @Scheduled(fixedRate = 1000)
    public void start() {
        Collection<Student> students = generateInspections();
        log.info("->New student: " + students.stream()
                .map(Student::toString)
                .collect(joining("\n")));

        Collection<Report> reports = reportGateway.process(students);
        log.info("->Result: " + reports.stream()
                .map(Report::toString)
                .collect(joining("\n")));
    }

    private static Collection<Student> generateInspections() {
        return IntStream.range(0, random.nextInt(1, 5))
                .mapToObj(i -> new Student(random.nextLong()))
                .collect(toList());
    }
}