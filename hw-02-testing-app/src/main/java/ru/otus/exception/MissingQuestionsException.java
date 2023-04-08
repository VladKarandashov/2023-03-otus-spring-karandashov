package ru.otus.exception;

public class MissingQuestionsException extends RuntimeException {

    public MissingQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
