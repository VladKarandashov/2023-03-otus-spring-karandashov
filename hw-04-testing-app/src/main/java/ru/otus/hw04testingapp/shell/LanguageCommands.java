package ru.otus.hw04testingapp.shell;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ru.otus.hw04testingapp.props.LocaleProvider;

import java.util.Locale;

@Component
@ShellComponent
@ShellCommandGroup("language settings")
public class LanguageCommands {

    private final LocaleProvider localeProvider;

    public LanguageCommands(LocaleProvider localeProvider) {
        this.localeProvider = localeProvider;
    }

    @ShellMethod(value = "change testing language", key = {"l", "language"})
    public void changeTestingLanguage(@ShellOption Locale locale) {
        localeProvider.setLocale(locale);
    }
}
