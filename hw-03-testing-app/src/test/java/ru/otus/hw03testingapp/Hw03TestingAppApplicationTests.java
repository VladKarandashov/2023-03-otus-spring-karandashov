package ru.otus.hw03testingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
class Hw03TestingAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
