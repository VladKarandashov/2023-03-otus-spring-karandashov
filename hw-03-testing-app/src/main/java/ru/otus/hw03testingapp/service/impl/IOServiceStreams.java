package ru.otus.hw03testingapp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw03testingapp.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreams implements IOService {

    private final PrintStream out;

    private final Scanner in;

    public IOServiceStreams(@Value("#{ T(java.lang.System).out}") PrintStream out,
                            @Value("#{ T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.in = new Scanner(in);
    }

    @Override
    public void printLn(String message) {
        out.println(message);
    }

    @Override
    public void print(String message) {
        out.print(message);
    }

    @Override
    public String readLn() {
        return in.next();
    }

}