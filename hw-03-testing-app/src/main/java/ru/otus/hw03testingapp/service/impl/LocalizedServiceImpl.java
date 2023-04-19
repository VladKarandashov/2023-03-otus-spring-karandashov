package ru.otus.hw03testingapp.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03testingapp.props.ApplicationProps;
import ru.otus.hw03testingapp.service.LocalizedService;

import java.util.Locale;

@Service
public class LocalizedServiceImpl implements LocalizedService {

    private final MessageSource messageSource;

    private final ApplicationProps applicationProps;

    public LocalizedServiceImpl(MessageSource messageSource, ApplicationProps applicationProps) {
        this.messageSource = messageSource;
        this.applicationProps = applicationProps;
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, applicationProps.getLocale());
    }

    @Override
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, applicationProps.getLocale());
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}
