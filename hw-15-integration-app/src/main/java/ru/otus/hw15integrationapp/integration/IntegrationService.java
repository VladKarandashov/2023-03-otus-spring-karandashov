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
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegrationService {

    private static final Random random = new Random();

    private final ReportGateway reportGateway;

    private Long studentGroupCounter = 0L;

    @Async
    @Scheduled(fixedRate = 20*1000)
    public void start() {
        var groupNumber = ++studentGroupCounter;
        log.info("Создаю {} группу студентов", groupNumber);

        Collection<Student> students = LongStream.range(1, random.nextInt(1, 5))
                .mapToObj(i -> new Student(groupNumber, i))
                .peek(student -> log.info("-> Новый студент {}", student))
                .collect(toList());

        log.info("Группа {} отправляется на обработку", groupNumber);
        Collection<Report> reports = reportGateway.process(students);
        log.info("Группа {} закончила обработку", groupNumber);
        reports.forEach(report -> log.info("-> Получен результат: {}", report));
    }
}