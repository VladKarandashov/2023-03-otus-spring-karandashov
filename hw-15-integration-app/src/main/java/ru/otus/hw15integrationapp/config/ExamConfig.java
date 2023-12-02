package ru.otus.hw15integrationapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "exam")
public class ExamConfig {

    Integer concurrentStudentsNumber = 5;

    Integer minimalDuration = 2*1000;
}