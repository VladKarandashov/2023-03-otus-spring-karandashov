package ru.otus.hw15integrationapp.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;

@MessagingGateway
public interface ReportGateway {

    @Gateway(requestChannel = "studentChannel", replyChannel = "reportChannel")
    Report process(Student student);
}