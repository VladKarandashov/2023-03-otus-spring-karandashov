package ru.otus.hw04testingapp.service;

import java.util.Locale;

public interface LocalizedService {

    String getMessage(String code);

    String getMessage(String code, Locale locale);

    String getMessage(String code, Object[] args);

    String getMessage(String code, Object[] args, Locale locale);
}
