package ru.otus.hw03testingapp.exception;

public class InvalidFileStructureException extends RuntimeException {
    public InvalidFileStructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
