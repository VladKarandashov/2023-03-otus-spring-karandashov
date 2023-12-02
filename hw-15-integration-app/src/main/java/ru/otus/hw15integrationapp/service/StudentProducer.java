package ru.otus.hw15integrationapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.otus.hw15integrationapp.integration.ReportGateway;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentProducer {

    private final ReportGateway reportGateway;

    private final AtomicInteger counter = new AtomicInteger(0);

    @Async
    @Scheduled(fixedRate = 2 * 1000)
    public void start() {

        var student = new Student(counter.incrementAndGet());

        log.info("НОВЫЙ студент {} отправляется на экзамен", student.getId());
        Report report = reportGateway.process(student);
        log.info("Студент {} получил {} за экзамен", student.getId(), report.getTestResult());
    }
}