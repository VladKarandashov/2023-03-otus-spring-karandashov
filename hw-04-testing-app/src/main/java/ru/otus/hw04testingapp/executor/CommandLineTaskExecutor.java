package ru.otus.hw04testingapp.executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.otus.hw04testingapp.service.TestingService;

@Component
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class CommandLineTaskExecutor implements CommandLineRunner {

    private final TestingService service;

    public CommandLineTaskExecutor(TestingService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        service.testing();
    }
}