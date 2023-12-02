package ru.otus.hw15integrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableIntegration

@IntegrationComponentScan
@ConfigurationPropertiesScan

@SpringBootApplication
public class Hw15IntegrationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw15IntegrationAppApplication.class, args);
    }

}
