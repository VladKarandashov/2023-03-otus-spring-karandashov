package ru.otus.hw03testingapp.service;

public interface OutputService {
    void printString(String s);

    void printStringByLocale(String code);

    void printStringByLocale(String code, Object[] args);
}