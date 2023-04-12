package ru.otus.hw03testingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Hw03TestingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw03TestingAppApplication.class, args);
    }

}
