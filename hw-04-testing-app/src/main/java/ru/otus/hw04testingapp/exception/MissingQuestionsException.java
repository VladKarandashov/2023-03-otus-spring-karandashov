package ru.otus.hw04testingapp.exception;

public class MissingQuestionsException extends RuntimeException {

    public MissingQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
