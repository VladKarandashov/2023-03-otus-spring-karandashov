package ru.otus.hw15integrationapp.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw15integrationapp.model.Report;
import ru.otus.hw15integrationapp.model.Student;

import java.util.Collection;

@MessagingGateway
public interface ReportGateway {

    @Gateway(requestChannel = "examChannel", replyChannel = "reportChannel")
    Collection<Report> process(Collection<Student> students);
}