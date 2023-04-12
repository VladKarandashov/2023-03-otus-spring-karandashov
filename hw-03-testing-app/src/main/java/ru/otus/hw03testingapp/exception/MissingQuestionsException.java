package ru.otus.hw03testingapp.exception;

public class MissingQuestionsException extends RuntimeException {

    public MissingQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
