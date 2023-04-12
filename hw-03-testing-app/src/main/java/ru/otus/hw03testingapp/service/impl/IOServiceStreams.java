package ru.otus.hw03testingapp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03testingapp.props.ApplicationProps;
import ru.otus.hw03testingapp.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreams implements IOService {

    private final PrintStream out;

    private final Scanner in;

    private final MessageSource messageSource;

    private final ApplicationProps applicationProps;

    public IOServiceStreams(@Value("#{ T(java.lang.System).out}") PrintStream out,
                            @Value("#{ T(java.lang.System).in}") InputStream in,
                            MessageSource messageSource,
                            ApplicationProps applicationProps) {
        this.out = out;
        this.in = new Scanner(in);
        this.messageSource = messageSource;
        this.applicationProps = applicationProps;
    }

    @Override
    public void printString(String message) {
        out.println(message);
    }

    public void printStringByLocale(String code) {
        out.println(
                messageSource.getMessage(code, null, applicationProps.getLocale())
        );
    }

    public void printStringByLocale(String code, Object[] args) {
        out.println(
                messageSource.getMessage(code, args, applicationProps.getLocale())
        );
    }

    @Override
    public String readString() {
        return in.next();
    }

}