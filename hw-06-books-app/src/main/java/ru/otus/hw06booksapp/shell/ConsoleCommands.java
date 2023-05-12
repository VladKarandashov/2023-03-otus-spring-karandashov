package ru.otus.hw06booksapp.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("Additional commands for H2")
public class ConsoleCommands {
    @ShellMethod(value = "Open web H2 console", key = {"console"})
    public String openConsoleH2() {
        try {
            Console.main();
        } catch (Exception e) {
            return "Console can't start";
        }
        return "Console start in browser";
    }
}