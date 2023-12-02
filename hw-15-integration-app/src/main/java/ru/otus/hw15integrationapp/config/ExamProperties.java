package ru.otus.hw15integrationapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "exam")
public class ExamProperties {

    private Integer studentChannelCapacity = 100;

    private Integer concurrentStudentsNumber = 10;

    private Integer minimalDuration = 2*1000;
}