package ru.otus.hw04testingapp.shell;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.otus.hw04testingapp.service.TestingService;

@Component
@ShellComponent
@ShellCommandGroup("testing commands")
public class TestingCommands {

    private final TestingService testingService;

    public TestingCommands(TestingService testingService) {
        this.testingService = testingService;
    }

    @ShellMethod(value = "start math testing", key = {"s", "start"})
    public void startTesting() {
        testingService.testing();
    }
}
