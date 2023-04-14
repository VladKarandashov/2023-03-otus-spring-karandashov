package ru.otus.hw03testingapp.service;

import java.util.Locale;

public interface LocalizedService {

    String getLocalizedString(String code);

    String getLocalizedString(String code, Locale locale);

    String getLocalizedString(String code, Object[] args);

    String getLocalizedString(String code, Object[] args, Locale locale);
}
